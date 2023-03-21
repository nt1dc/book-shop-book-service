package se.nt1dc.bookservice.controllers

import org.springframework.web.bind.annotation.*
import se.nt1dc.bookservice.dto.OrderDto
import se.nt1dc.bookservice.service.OrderService

@RestController
@RequestMapping("/order")
class OrderController(
    val orderService: OrderService
) {


    @PostMapping("")
    fun createOrder(orderDto: OrderDto, userId: Int) {
        orderService.createOrder(orderDto, userId);
    }

    @GetMapping("/pay/{orderId}")
    fun getOrder(@PathVariable orderId: Int): String {
        return orderService.pay(orderId)
    }

    @GetMapping("/updateStatus/{orderId}")
    fun updateOrderStatus(@PathVariable orderId: Int) {
        orderService.updateStatus(orderId)
    }
}