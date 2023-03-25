package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.entity.Item
import se.nt1dc.bookservice.repository.BookRepository

@Service
class ItemService(val bookRepository: BookRepository) {
    fun findItemsByBookIdList(booksIds: MutableList<Int>?): MutableList<Item>? {
        val itemList = booksIds?.groupingBy { it }?.eachCount()?.map {
            val book = bookRepository.findById(it.key).orElseThrow { throw RuntimeException() }
            val itemMutableList =
                book.physicalBook.items.stream().filter { item -> item.available }.limit(it.value.toLong()).toList()
            if (itemMutableList.size < it.value) throw RuntimeException(
                book.name + " доступно только " + itemMutableList.size + " из" + it.value
            )
            itemMutableList
        }?.flatten()?.toMutableList()
        return itemList
    }
}