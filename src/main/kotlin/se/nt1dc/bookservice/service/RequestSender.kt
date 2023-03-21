package se.nt1dc.bookservice.service

import org.aspectj.apache.bcel.classfile.JavaClass
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI

@Service
class RequestSender {
    val restTemplate: RestTemplate = RestTemplate()

    @Value("\${apiGatewayAddress}")
    lateinit var apiGateWayAddress: String
    fun sendReq(url: String, body: Any): ResponseEntity<Any> {
        return restTemplate.exchange(URI(apiGateWayAddress + url), HttpMethod.GET, HttpEntity(body), body.javaClass)
    }
}