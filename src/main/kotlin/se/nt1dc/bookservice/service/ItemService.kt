package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dto.BookIdWithCount
import se.nt1dc.bookservice.dto.BookWithCount
import se.nt1dc.bookservice.entity.Item
import se.nt1dc.bookservice.repository.BookRepository

@Service
class ItemService(val bookRepository: BookRepository) {
    fun findItemsByBookIdList(books: MutableList<BookIdWithCount>): MutableList<Item>? {
        val items = books.stream().map { bookIdWithCount ->
            val book = bookRepository.findById(bookIdWithCount.bookId).orElseThrow { throw RuntimeException() }
            BookWithCount(
                book,
                bookIdWithCount.count
            )
        }.map { bookWithCount ->
            if (bookWithCount.book.items.size < bookWithCount.count) throw RuntimeException(
                bookWithCount.book.name + " доступно только " + bookWithCount.book.items.size + " из" + bookWithCount.count
            )
            bookWithCount.book.items.stream().filter { it.available }.limit(bookWithCount.count.toLong())
        }.flatMap { it }.toList()
        return items
    }
}