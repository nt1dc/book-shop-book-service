package se.nt1dc.bookservice.service

import jakarta.transaction.Transactional
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dto.ItemShippingDto
import se.nt1dc.bookservice.dto.ItemShippingReq
import se.nt1dc.bookservice.exceptions.ForeingServerRequestException
import se.nt1dc.bookservice.dto.LocationDto
import se.nt1dc.bookservice.entity.Item

@Service
@Transactional
class ShippingServiceImpl(
    val requestSender: RequestSender
) : ShippingService {

    override fun calculateShipping(items: MutableList<Item>?, to: LocationDto): Double? {
        val itemShipRequest = items?.stream()?.map {
            ItemShippingDto(
                LocationDto(it.stock.location.latitude, it.stock.location.longitude),
                to,
                it.book.length,
                it.book.width,
                it.book.height,
                it.book.weight
            )
        }?.toList()
        val shipPriceSumResponse =
            requestSender.sendReq("/delivery-service/calculate", ItemShippingReq(itemShipRequest), HttpMethod.POST)
        if (shipPriceSumResponse.statusCode.is2xxSuccessful) return shipPriceSumResponse.body?.toDouble()
        throw ForeingServerRequestException(shipPriceSumResponse)
    }

}