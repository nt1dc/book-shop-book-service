package se.nt1dc.bookservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.nt1dc.bookservice.dto.DigitalBookResponse
import se.nt1dc.bookservice.dto.UserCreationRequest
import se.nt1dc.bookservice.entity.User
import se.nt1dc.bookservice.service.UserService

@RequestMapping("/user")
@RestController
class UserController(val userService: UserService) {

    @GetMapping("/{userId}/digitalBooks/")
    fun findUserBooks(@PathVariable userId: Int): MutableList<DigitalBookResponse> {
        return userService.findUserDigitalBooks(userId)
    }

    @GetMapping("/{userId}")
    fun gitUser(@PathVariable userId: Int): User? {
        return userService.getUserById(userId)
    }

    @PostMapping()
    fun createUser(@RequestBody userCreationRequest: UserCreationRequest) {
        userService.createUser(userCreationRequest)
    }
}