package se.nt1dc.bookservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.netflix.discovery.EurekaClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.DELETE
import org.springframework.http.HttpMethod.GET
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import se.nt1dc.bookservice.dto.order.OrderDto
import se.nt1dc.bookservice.dto.payment.CreatePaymentOrderReq
import se.nt1dc.bookservice.dto.payment.CreatePaymentOrderResp
import se.nt1dc.bookservice.entity.Order
import se.nt1dc.bookservice.entity.OrderStatus
import se.nt1dc.bookservice.entity.PaymentStatus
import se.nt1dc.bookservice.repository.OrderRepository
import se.nt1dc.bookservice.repository.UserRepository

@Service
@Transactional
class OrderService(
    val requestSender: RequestSender,
    val orderRepository: OrderRepository,
    val objectMapper: ObjectMapper = ObjectMapper(),
    val digitalBooksService: DigitalBooksService,
    val userRepository: UserRepository,
    val itemService: ItemService,
    val physicalOrderService: PhysicalOrderService,
    val userService: UserService,
) {

    var apiGatewayAddress: String = ""
    val shopPaymentAccountId: Int = 1

    fun createOrder(orderDto: OrderDto, login: String): Int? {
        val user = userService.getUserByName(login)
        val digitalBooks = digitalBooksService.findBooksByIds(orderDto.digitalOrder?.digitalBooksIds)
        val items = itemService.findItemsByBookIdList(orderDto.physicalOrder?.physicalBooksIds)
        val physicalBooksWithDeliveryPrice = physicalOrderService.calculateTotalPrice(items, orderDto.physicalOrder?.to)
        val digitalBooksPrice = digitalBooks?.stream()?.mapToDouble { it.price }?.sum()
        val totalPrice = digitalBooksPrice?.plus(physicalBooksWithDeliveryPrice!!)

        val responseEntity = requestSender.sendReq(
            "payment-service/payment/create", CreatePaymentOrderReq(totalPrice, shopPaymentAccountId), HttpMethod.POST
        )
        if (!responseEntity.statusCode.is2xxSuccessful) throw RuntimeException("проблема создания платежа")
        val createPaymentOrderResp = objectMapper.readValue(responseEntity.body, CreatePaymentOrderResp::class.java)
        try {
            val order = Order(
                status = OrderStatus.CREATED,
                items = items,
                user = user,
                paymentId = createPaymentOrderResp.paymentOrderId,
                digitalBooks = digitalBooks
            )
            orderRepository.save(order)
            user.orders.add(order)
            items?.stream()?.forEach { it.available = false }

            userRepository.saveAndFlush(user)
            return order.id
        } catch (e: RuntimeException) {
            requestSender.sendReqWithoutBody("payment-service/payment/${createPaymentOrderResp.paymentOrderId}", DELETE)
        }
        throw throw RuntimeException("проблема создания платежа")
    }

    fun pay(orderId: Int): String {
        val order = orderRepository.findById(orderId).orElseThrow { RuntimeException("order not found") }
        return "http://" + "$apiGatewayAddress." + "payment-service/payment/pay/" + order.paymentId
    }

    fun updateStatus(orderId: Int) {
        val order = orderRepository.findById(orderId).orElseThrow { RuntimeException("order not found") }
        val sendReqWithoutBody =
            requestSender.sendReqWithoutBody("/payment-service/payment/status/".plus(order.paymentId), GET)
        if (!sendReqWithoutBody.statusCode.is2xxSuccessful) throw RuntimeException("getting status Error")

        val newOrderStatus =
            OrderStatus.valueOf(objectMapper.readValue(sendReqWithoutBody.body, PaymentStatus::class.java).toString())
        if (order.status != newOrderStatus) {
            order.status = newOrderStatus
            if (newOrderStatus == OrderStatus.PAYED) order.digitalBooks?.let { order.user.digitalBooks.addAll(it) }
        }
        orderRepository.save(order)
    }
}