package se.nt1dc.bookservice.service.schedule.kafkaProducer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import se.nt1dc.avro.users.NewUsersInfo

@Component
class KafkaProducer(
    var kafkaTemplate: KafkaTemplate<String, NewUsersInfo>,
) {
    fun send(newUsers: NewUsersInfo) =
        kafkaTemplate.send("new-users-info-topic", newUsers.dateTo, newUsers)

}