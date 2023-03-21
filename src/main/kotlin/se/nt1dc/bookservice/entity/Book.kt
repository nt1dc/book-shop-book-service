package se.nt1dc.bookservice.entity

import jakarta.persistence.*

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    var name: String,
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    var items: MutableList<Item> = mutableListOf(),
    var length: Int,
    var width: Int,
    var height: Int,
    var weight: Int,
    var price: Double
)
