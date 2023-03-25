package se.nt1dc.bookservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class RequestSender {
    private val restTemplate: RestTemplate = RestTemplate()

    @Value("\${apiGatewayAddress}")
    lateinit var apiGateWayAddress: String
    fun sendReq(url: String, body: Any?, httpMethod: HttpMethod): ResponseEntity<String> {
        return restTemplate.exchange(
            URI(apiGateWayAddress + url),
            httpMethod,
            HttpEntity(body as Any),
            String::class.java
        )
    }

    fun sendReqWithoutBody(url: String, httpMethod: HttpMethod): ResponseEntity<String> {
        return restTemplate.exchange(URI(apiGateWayAddress.plus(url)), HttpMethod.GET, null, String::class.java)
    }
}