package se.nt1dc.bookservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "\"order\"")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    @Enumerated(EnumType.STRING)
    var status: OrderStatus,
    @OneToMany(mappedBy = "order")
    var items: List<Item>,
    @ManyToOne()
    @JoinColumn(name = "user_id")
    var user: User
)