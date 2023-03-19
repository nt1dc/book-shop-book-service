package se.nt1dc.bookservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

@Entity
data class Book(
    @Id
    val id: Int,
    val name: String,
    @OneToMany
    @JoinColumn(name = "book_id")
    val items: MutableSet<Item>
)
