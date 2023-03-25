package se.nt1dc.bookservice.dto

data class OrderDto(
    var userId: Int,
    var digitalOrder: DigitalOrder?,
    var physicalOrder: PhysicalOrder?,
)
