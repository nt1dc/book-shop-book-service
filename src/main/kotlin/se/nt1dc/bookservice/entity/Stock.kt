package se.nt1dc.bookservice.entity

import jakarta.persistence.*

@Entity
data class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    @OneToOne
    var location: Location,
    @OneToMany(mappedBy = "stock")
    var items: Set<Item>
)