package se.nt1dc.bookservice.exceptions

import org.springframework.http.ResponseEntity

class ForeingServerRequestException(responseEntity: ResponseEntity<Any>) : RuntimeException() {
}