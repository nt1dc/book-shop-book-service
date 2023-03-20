package se.nt1dc.bookservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import se.nt1dc.bookservice.dto.OrderDto
import se.nt1dc.bookservice.service.ShippingService

@RestController
class OrderController(val shippingService: ShippingService) {
    @GetMapping
    fun calculateShippingPrice(orderDto: OrderDto): String? {
        return shippingService.calculateShipping(orderDto);
    }
}