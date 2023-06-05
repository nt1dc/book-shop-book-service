package se.nt1dc.bookservice.dto.shipping

data class ItemShippingReq(
    var items: MutableList<ItemShippingDto>? = mutableListOf()
)