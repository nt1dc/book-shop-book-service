package se.nt1dc.bookservice.dto

data class PhysicalOrder(
    var to: LocationDto? = LocationDto(Double.NaN, Double.NaN),
    var physicalBooksIds: MutableList<Int> = mutableListOf(),
)
