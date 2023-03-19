package se.nt1dc.bookservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.nt1dc.bookservice.entity.Book
import se.nt1dc.bookservice.service.BookService

@RestController
@RequestMapping("/book")
class BookController(val bookService: BookService) {
    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Int): Book {
        return bookService.findBookById(id);
    }

    @PostMapping
    fun addBook(book: Book) {
        bookService.addBook(book);
    }
}