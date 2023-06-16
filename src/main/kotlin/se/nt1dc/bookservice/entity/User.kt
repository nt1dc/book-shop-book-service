package se.nt1dc.bookservice.entity

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.util.Date

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var login: String,
    @OneToMany(mappedBy = "user")
    var orders: MutableList<Order> = mutableListOf(),
    @ManyToMany
    var digitalBooks: MutableList<DigitalBook> = mutableListOf(),
    var registrationDate: Date = Date()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , login = $login )"
    }
}
