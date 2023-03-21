package se.nt1dc.bookservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import se.nt1dc.bookservice.entity.Book

@EnableJpaRepositories
interface BookRepository : JpaRepository<Book, Int> {
}