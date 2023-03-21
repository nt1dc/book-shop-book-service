package se.nt1dc.bookservice.dto

data class ItemShippingReq(
    var items: MutableList<ItemShippingDto>? = mutableListOf()
)