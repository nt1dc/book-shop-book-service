package se.nt1dc.bookservice.dto.order

import se.nt1dc.bookservice.dto.LocationDto

data class PhysicalOrder(
    var to: LocationDto? = LocationDto(Double.NaN, Double.NaN),
    var physicalBooksIds: MutableList<Int> = mutableListOf(),
)
