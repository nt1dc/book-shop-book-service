package se.nt1dc.bookservice.dto.order

data class DigitalOrder(
    var digitalBooksIds: MutableSet<Int>
)
