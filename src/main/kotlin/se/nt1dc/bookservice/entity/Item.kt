package se.nt1dc.bookservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
data class Item(
    @Id
    var id: Int,
    @ManyToOne
    val book: Book,
    var count: Int,
    @ManyToOne()
    @JoinColumn(name = "stock_id")
    val stock: Stock
)
