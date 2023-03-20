package se.nt1dc.bookservice.dto

data class ItemShippingRequest(
    var from: String,
    var to: String,
    val size: Int
)