package se.nt1dc.bookservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableKafka
@EnableDiscoveryClient
@EnableScheduling
class BookServiceApplication

fun main(args: Array<String>) {
    runApplication<BookServiceApplication>(*args)
}

