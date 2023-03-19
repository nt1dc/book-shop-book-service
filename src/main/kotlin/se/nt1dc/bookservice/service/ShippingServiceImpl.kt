package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dto.OrderDto

@Service
class ShippingServiceImpl : ShippingService {
    override fun calculateShipping(orderDto: OrderDto) {

    }
}