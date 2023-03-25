package se.nt1dc.bookservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "\"user\"")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var email: String,
    @OneToMany(mappedBy = "user")
    var orders: MutableList<Order>,
    @ManyToMany
    var digitalBooks: MutableList<DigitalBook> = mutableListOf()
)
