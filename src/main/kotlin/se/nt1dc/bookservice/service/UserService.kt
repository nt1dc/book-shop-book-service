package se.nt1dc.bookservice.service

import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dto.DigitalBookResponse
import se.nt1dc.bookservice.dto.user.UserCreationRequest
import se.nt1dc.bookservice.entity.User
import se.nt1dc.bookservice.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
    fun findUserDigitalBooks(userId: Int): MutableList<DigitalBookResponse> {
        return userRepository.findById(userId).orElseThrow { RuntimeException() }.digitalBooks.stream()
            .map { DigitalBookResponse(it.book.name, it.downloadLink) }.toList()
    }

    fun getUserById(userId: Int): User {
        return userRepository.findById(userId).orElseThrow { RuntimeException("user not found") }
    }

    fun getUserByName(login: String): User {
        return userRepository.findByLogin(login).orElseThrow { RuntimeException("user $login not found") }
    }

    fun createUser(userCreationRequest: UserCreationRequest) {
        userRepository.save(
            User(
                login = userCreationRequest.login
            )
        )
    }

    fun deleteUserByLogin(login: String) {
        userRepository.deleteByLogin(login)
    }
}
