package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.entity.DigitalBook
import se.nt1dc.bookservice.repository.DigitalBookRepository

@Service
class DigitalBooksService(val digitalBookRepository: DigitalBookRepository) {
    fun findBooksByIds(booksIds: MutableSet<Int>?): MutableList<DigitalBook>? {
        return booksIds?.let { digitalBookRepository.findAllById(it) }
    }


}