package se.nt1dc.bookservice.dao

import se.nt1dc.bookservice.entity.Book

data class BookWithCount(
    var book: Book,
    var count: Int
)