package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dto.LocationDto
import se.nt1dc.bookservice.dto.OrderDto
import se.nt1dc.bookservice.entity.Item

@Service
interface ShippingService {
    fun calculateShipping(items: MutableList<Item>?, to: LocationDto): Double?

}
