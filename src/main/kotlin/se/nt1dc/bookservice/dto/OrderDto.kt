package se.nt1dc.bookservice.dto

data class OrderDto(
    var digitalOrder: DigitalOrder?,
    var physicalOrder: PhysicalOrder?,
)
