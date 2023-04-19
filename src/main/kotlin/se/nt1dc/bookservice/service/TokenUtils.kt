package se.nt1dc.bookservice.service

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service

@Service
class TokenUtils(val requestSender: RequestSender) {
    fun extractUserNameFromToken(httpServletRequest: HttpServletRequest): String {
        val sendReqWithoutBody = requestSender.sendReqWithoutBody("auth-service/token/getName", HttpMethod.GET)
        return sendReqWithoutBody.body!!
    }
}