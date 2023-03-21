package se.nt1dc.bookservice.entity

import jakarta.persistence.*

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    @OneToMany
    @JoinColumn(name = "book_id")
    val items: MutableSet<Item>,
    val length: Int,
    val width: Int,
    val height: Int,
    val weight: Int
)
