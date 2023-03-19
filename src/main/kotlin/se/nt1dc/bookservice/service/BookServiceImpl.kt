package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.entity.Book

@Service
class BookServiceImpl : BookService {
    override fun findBookById(id: Int): Book {
        TODO("Not yet implemented")
    }

    override fun addBook(book: Book) {
        TODO("Not yet implemented")
    }
}