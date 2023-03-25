package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dto.LocationDto
import se.nt1dc.bookservice.entity.Item

@Service
class PhysicalOrderService(
    var shippingService: ShippingService
) {
    fun calculateTotalPrice(items: MutableList<Item>?, to: LocationDto?): Double? {
        if (items?.isNotEmpty() == true) {
            val shippingPrice = shippingService.calculateShipping(items, to)
            val booksPrice = items.stream().mapToDouble { it.physicalBook.price }?.sum()
            return shippingPrice?.plus(booksPrice!!)
        }
        return 0.0
    }
}