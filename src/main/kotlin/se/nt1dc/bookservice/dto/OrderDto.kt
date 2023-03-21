package se.nt1dc.bookservice.dto

data class OrderDto(
    val to: LocationDto = LocationDto(Double.NaN, Double.NaN),
    val bookOrderList: MutableList<BookIdWithCount> = mutableListOf()
)
