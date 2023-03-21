package se.nt1dc.bookservice.dto

import se.nt1dc.bookservice.entity.Book

data class BookWithCount(
    var book: Book,
    var count: Int
)
