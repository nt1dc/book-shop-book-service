package se.nt1dc.bookservice.service

import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class RequestSender(
    val restTemplate: RestTemplate
) {
    val protocol: String = "http://"
    fun sendReq(url: String, body: Any?, httpMethod: HttpMethod): ResponseEntity<String> {
        return restTemplate.exchange(
            URI(protocol + url),
            httpMethod,
            HttpEntity(body as Any),
            String::class.java
        )
    }

    fun sendReqWithoutBody(url: String, httpMethod: HttpMethod): ResponseEntity<String> {
        return restTemplate.exchange(URI(protocol + url), HttpMethod.GET, null, String::class.java)
    }
}