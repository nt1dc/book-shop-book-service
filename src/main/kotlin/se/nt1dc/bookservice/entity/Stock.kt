package se.nt1dc.bookservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Stock(
    @Id
    var id: Int,
    var location: String,
    @OneToMany(mappedBy = "stock")
    var items: Set<Item>
)