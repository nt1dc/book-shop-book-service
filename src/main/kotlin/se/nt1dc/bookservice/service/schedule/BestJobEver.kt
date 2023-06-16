package se.nt1dc.bookservice.service.schedule

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Service
import se.nt1dc.avro.users.NewUsersInfo
import se.nt1dc.bookservice.repository.UserRepository
import se.nt1dc.bookservice.service.schedule.kafkaProducer.KafkaProducer
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Service
class BestJobEver
    (
    val userRepository: UserRepository,
    val kafkaProducer: KafkaProducer
) : Job {

    val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    fun findNewRegisteredUsers(dateFrom: Date, dateTo: Date) =
        userRepository.findAllByRegistrationDateBetween(dateFrom, dateTo)


    override fun execute(context: JobExecutionContext?) {
        val dateTo = Date()
        val dateFrom = Date(dateTo.time - dateBeforeInterval)
        val newUsers = findNewRegisteredUsers(dateFrom, dateTo)
        val newUsersInfo = NewUsersInfo.newBuilder()
            .setDateFrom(dateFormat.format(dateFrom))
            .setDateTo(dateFormat.format(dateTo))
            .setCount(newUsers.count()).build()

        kafkaProducer.send(newUsersInfo)
    }

    companion object {
                const val dateBeforeInterval = 1000 * 60 * 60 * 24
//        const val dateBeforeInterval = 1000 * 5
    }

}
