package se.nt1dc.bookservice.model

import org.springframework.stereotype.Service
import java.io.Serializable


@Service
data class Order(
    val id: String = ""
) : Serializable