package se.nt1dc.bookservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.nt1dc.bookservice.dto.OrderDto
import se.nt1dc.bookservice.service.ItemService
import se.nt1dc.bookservice.service.ShippingService

@RestController
@RequestMapping("/shipping")
class ShippingController(val shippingService: ShippingService, val itemService: ItemService) {
    @GetMapping()
    fun calculateShippingPrice(orderDto: OrderDto): Double? {
        return shippingService.calculateShipping(itemService.findItemsByBookIdList(orderDto.bookOrderList),
            orderDto.to
        )
    }
}