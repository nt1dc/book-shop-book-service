package se.nt1dc.bookservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import se.nt1dc.bookservice.entity.Book

interface BookRepository : JpaRepository<Book, Int> {
    fun findAllByName(name: MutableList<String>): MutableSet<Book>
    fun findByName(name:String):Book
}