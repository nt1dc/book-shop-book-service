package se.nt1dc.bookservice.entity

import jakarta.persistence.*

@Entity
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    @ManyToOne
    val book: Book,
    @ManyToOne
    @JoinColumn(name = "stock_id")
    val stock: Stock,
    var available: Boolean = true
)
