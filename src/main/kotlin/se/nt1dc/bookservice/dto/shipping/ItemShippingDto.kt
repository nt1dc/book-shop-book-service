package se.nt1dc.bookservice.dto.shipping

import se.nt1dc.bookservice.dto.LocationDto

data class ItemShippingDto(
    var from: LocationDto,
    var to: LocationDto?,
    val length: Int,
    val width: Int,
    val height: Int,
    val weight: Int
)