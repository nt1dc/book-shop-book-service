package se.nt1dc.bookservice.controllers


import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class PingPongController(
    var kafkaTemplateV1: KafkaTemplate<String, se.nt1dc.avro.orders.OrderValue>,
//    var kafkaTemplateV2: KafkaTemplate<String, se.nt1dc.avro.ordersV2.OrderValue>
) {

    @GetMapping("/ping")
    fun ping(): String {
        val order = se.nt1dc.avro.orders.OrderValue.newBuilder()
            .setId(UUID.randomUUID().toString())
            .build()
        kafkaTemplateV1.send("best-topic", order.id, order);
        return "pong"
    }
//    @GetMapping("/pingV2")
//    fun pingV2(): String {
//        val order = se.nt1dc.avro.ordersV2.OrderValue.newBuilder()
//            .setId(UUID.randomUUID().toString())
//            .setNewField("newFieldValue")
//            .build()
//        kafkaTemplateV2.send("best-topic", order.id, order);
//        return "pong"
//    }
}