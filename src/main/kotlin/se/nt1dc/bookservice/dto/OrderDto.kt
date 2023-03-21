package se.nt1dc.bookservice.dto

data class OrderDto(
    val to: LocationDto,
    val bookOrderList: MutableList<BookIdWithCount>
)
