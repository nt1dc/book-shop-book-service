package se.nt1dc.bookservice.dto.order

data class OrderDto(
    var digitalOrder: DigitalOrder?,
    var physicalOrder: PhysicalOrder?,
)
