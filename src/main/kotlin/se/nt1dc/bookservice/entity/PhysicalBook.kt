package se.nt1dc.bookservice.entity

import jakarta.persistence.*

@Entity
data class PhysicalBook(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?=null,
    var length: Int,
    var width: Int,
    var height: Int,
    var weight: Int,
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "physical_book_id")
    var items: MutableList<Item> = mutableListOf(),
    var price: Double,
    @OneToOne()
    @JoinColumn(name = "book_id")
    var book: Book
)