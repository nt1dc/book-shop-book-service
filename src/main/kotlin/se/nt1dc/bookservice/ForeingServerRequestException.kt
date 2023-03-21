package se.nt1dc.bookservice

import org.springframework.http.ResponseEntity

class ForeingServerRequestException(responseEntity: ResponseEntity<Any>) : RuntimeException() {
}