package se.nt1dc.bookservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import se.nt1dc.bookservice.entity.User
import java.util.*

interface UserRepository : JpaRepository<User, Int> {
    fun findByLogin(login: String): Optional<User>
    fun deleteByLogin(login: String)
    fun findAllByRegistrationDateBetween(from: Date, to: Date): List<User>
}