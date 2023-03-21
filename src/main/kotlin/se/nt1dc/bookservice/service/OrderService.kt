package se.nt1dc.bookservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.GET
import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dto.CreatePaymentOrderReq
import se.nt1dc.bookservice.dto.CreatePaymentOrderResp
import se.nt1dc.bookservice.dto.OrderDto
import se.nt1dc.bookservice.entity.Order
import se.nt1dc.bookservice.entity.OrderStatus
import se.nt1dc.bookservice.repository.BookRepository
import se.nt1dc.bookservice.repository.OrderRepository
import se.nt1dc.bookservice.repository.UserRepository

@Service
class OrderService(
    val shippingService: ShippingService,
    val bookRepository: BookRepository,
    val requestSender: RequestSender,
    val userRepository: UserRepository,
    val itemService: ItemService,
    val orderRepository: OrderRepository
) {

    @Value("\${apiGatewayAddress}")
    lateinit var apiGateWayAddress: String

    fun createOrder(orderDto: OrderDto, userId: Int): Int? {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("user not found") }
        val items = itemService.findItemsByBookIdList(orderDto.bookOrderList)
        val shippingPrice = shippingService.calculateShipping(items, orderDto.to)
        val booksPrice = orderDto.bookOrderList.stream()
            .mapToDouble { bookRepository.findById(it.bookId).orElseThrow { RuntimeException() }.price * it.count }
            .sum()

        val responseEntity = requestSender.sendReq(
            "payment-service/payment/create",
            CreatePaymentOrderReq((shippingPrice?.plus(booksPrice)), 1),
            HttpMethod.POST
        )
        if (!responseEntity.statusCode.is2xxSuccessful) throw RuntimeException("проблема создания платежа")

        val createPaymentOrderResp = responseEntity.body as CreatePaymentOrderResp
        val order = Order(
            status = OrderStatus.CREATED,
            items = items,
            user = user,
            paymentId = createPaymentOrderResp.paymentOrderId
        )
        user.orders.add(
            order
        )
        userRepository.save(user)
        return order.id
    }

    fun pay(orderId: Int): String {
        val order = orderRepository.findById(orderId).orElseThrow { RuntimeException("order not found") }
        return apiGateWayAddress + "payment-service/payment/pay/" + order.paymentId
    }

    fun updateStatus(orderId: Int) {
        val order = orderRepository.findById(orderId).orElseThrow { RuntimeException("order not found") }
        val sendReqWithoutBody =
            requestSender.sendReqWithoutBody("payment-serive/payment/status".plus(order.paymentId), GET)
        if (!sendReqWithoutBody.statusCode.is2xxSuccessful) throw RuntimeException("getting status Error")
        order.status = OrderStatus.valueOf(sendReqWithoutBody.body.toString())
    }
}