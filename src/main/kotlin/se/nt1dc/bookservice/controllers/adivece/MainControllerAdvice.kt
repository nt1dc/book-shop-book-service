package se.nt1dc.bookservice.controllers.adivece

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class MainControllerAdvice : ResponseEntityExceptionHandler() {
//    @ExceptionHandler(value = [Exception::class])
//    fun handleConflict(exception: java.lang.Exception): ResponseEntity<Any> {
//        return ResponseEntity.status(500).body(exception.message)
//    }
}