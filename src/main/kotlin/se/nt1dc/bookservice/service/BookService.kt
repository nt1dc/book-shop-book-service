package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.entity.Book

@Service
interface BookService {
    fun findBookById(id: Int): Book
    fun addBook(book: Book)
}
