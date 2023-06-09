package se.nt1dc.bookservice.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "\"order\"")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Enumerated(EnumType.STRING)
    var status: OrderStatus,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "\"order_id\"")
    var items: MutableList<Item>?,

    @ManyToMany
    var digitalBooks: MutableList<DigitalBook>?,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,
    var paymentId: Int,
    var creationDate: Date = Date()
)