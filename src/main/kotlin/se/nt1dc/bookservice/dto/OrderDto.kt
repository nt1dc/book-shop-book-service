package se.nt1dc.bookservice.dto

data class OrderDto(
    val destinationAddress: String,
    val bookOrderList: List<BookOrderRequest>
)
