package se.nt1dc.bookservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.net.URI

class RequestSender {
    val restTemplate: RestTemplate = RestTemplate()

    @Value("\${apiGatewayAddress}")
    lateinit var apiGateWayAddress: String
    fun sendReq(url: String, body: Any): String? {
        return restTemplate.exchange(URI("zxc"), HttpMethod.GET, HttpEntity(body), String::class.java).body
    }
}