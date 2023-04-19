package se.nt1dc.bookservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.GET
import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dto.CreatePaymentOrderReq
import se.nt1dc.bookservice.dto.CreatePaymentOrderResp
import se.nt1dc.bookservice.dto.OrderDto
import se.nt1dc.bookservice.entity.Order
import se.nt1dc.bookservice.entity.OrderStatus
import se.nt1dc.bookservice.entity.PaymentStatus
import se.nt1dc.bookservice.repository.OrderRepository
import se.nt1dc.bookservice.repository.UserRepository
import java.util.*

@Service
class OrderService(
    val requestSender: RequestSender,
    val orderRepository: OrderRepository,
    val objectMapper: ObjectMapper = ObjectMapper(),
    val digitalBooksService: DigitalBooksService,
    val userRepository: UserRepository,
    val itemService: ItemService,
    val shippingService: ShippingService,
    val physicalOrderService: PhysicalOrderService,
    val userService: UserService,
    val tokenUtils: TokenUtils
) {

    @Value("\${apiGatewayAddress}")
    lateinit var apiGateWayAddress: String
    val shopPaymentAccountId: Int = 1

    fun createOrder(orderDto: OrderDto, httpServletRequest: HttpServletRequest): Int? {
        val user = userService.getUserByName(tokenUtils.extractUserNameFromToken(httpServletRequest))
        val digitalBooks = digitalBooksService.findBooksByIds(orderDto.digitalOrder?.digitalBooksIds)
        val items = itemService.findItemsByBookIdList(orderDto.physicalOrder?.physicalBooksIds)
        val physicalBooksWithDeliveryPrice = physicalOrderService.calculateTotalPrice(items, orderDto.physicalOrder?.to)
        val digitalBooksPrice = digitalBooks?.stream()?.mapToDouble { it.price }?.sum()
        val totalPrice = digitalBooksPrice?.plus(physicalBooksWithDeliveryPrice!!)

        val responseEntity = requestSender.sendReq(
            "/payment-service/payment/create", CreatePaymentOrderReq(totalPrice, shopPaymentAccountId), HttpMethod.POST
        )
        if (!responseEntity.statusCode.is2xxSuccessful) throw RuntimeException("проблема создания платежа")
        val createPaymentOrderResp = objectMapper.readValue(responseEntity.body, CreatePaymentOrderResp::class.java)
        val order = Order(
            status = OrderStatus.CREATED,
            items = items,
            user = user,
            paymentId = createPaymentOrderResp.paymentOrderId,
            digitalBooks = digitalBooks
        )
        orderRepository.save(order)
        user.orders.add(
            order
        )
        items?.stream()?.forEach { it.available = false }
        val saveAndFlush = userRepository.saveAndFlush(user)
        return saveAndFlush.id
    }

    fun pay(orderId: Int): String {
        val order = orderRepository.findById(orderId).orElseThrow { RuntimeException("order not found") }
        return apiGateWayAddress + "/payment-service/payment/pay/" + order.paymentId
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