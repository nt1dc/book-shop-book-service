package se.nt1dc.bookservice.service

import jakarta.transaction.Transactional
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import se.nt1dc.bookservice.exceptions.ForeingServerRequestException
import se.nt1dc.bookservice.dto.ItemShippingRequest
import se.nt1dc.bookservice.dto.LocationDto
import se.nt1dc.bookservice.entity.Item
import se.nt1dc.bookservice.repository.BookRepository

@Service
@Transactional
class ShippingServiceImpl(
    val bookRepository: BookRepository,
    val requestSender: RequestSender,
    val itemService: ItemService
) : ShippingService {

    override fun calculateShipping(items: MutableList<Item>?, to: LocationDto): Double? {
        val itemShipRequest = items?.stream()?.map {
            ItemShippingRequest(
                LocationDto(it.stock.location.latitude, it.stock.location.longitude),
                to,
                it.book.length,
                it.book.width,
                it.book.height,
                it.book.weight
            )
        }?.toList()
        val shipPriceSumResponse =
            requestSender.sendReq("delivery-service/calculate", itemShipRequest!!, HttpMethod.GET)
        if (shipPriceSumResponse.statusCode.is2xxSuccessful) return shipPriceSumResponse.body as Double
        throw ForeingServerRequestException(shipPriceSumResponse)
    }

}