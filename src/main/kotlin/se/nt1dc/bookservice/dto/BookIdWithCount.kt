package se.nt1dc.bookservice.dto

import jakarta.validation.constraints.NotNull

data class BookIdWithCount(
    @field:NotNull
    var bookId: Int=0,
    @field:NotNull
    var count: Int=0
)