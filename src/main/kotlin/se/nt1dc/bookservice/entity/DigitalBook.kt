package se.nt1dc.bookservice.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity
data class DigitalBook(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var price: Double,
    var downloadLink: String,
    @OneToOne()
    @JoinColumn(name = "book_id")
    var book: Book
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as DigitalBook

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , price = $price , downloadLink = $downloadLink , book = $book )"
    }
}