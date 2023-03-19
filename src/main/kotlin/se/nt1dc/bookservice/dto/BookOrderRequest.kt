package se.nt1dc.bookservice.dto

import se.nt1dc.bookservice.entity.Book

data class BookOrderRequest(
    val book: Book, val count: Int
)
