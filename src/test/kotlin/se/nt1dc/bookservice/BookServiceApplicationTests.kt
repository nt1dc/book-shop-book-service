package se.nt1dc.bookservice

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import se.nt1dc.bookservice.dto.LocationDto

class BookServiceApplicationTests {

    @Test
    fun contextLoads() {
        val list = HashMap<LocationDto, Int>()
        list[LocationDto(1, 2)] = list.getOrDefault(LocationDto(1, 2), 0) + 1
        list[LocationDto(1, 2)] = list.getOrDefault(LocationDto(1, 2), 0) + 1
        list[LocationDto(1, 2)] = list.getOrDefault(LocationDto(1, 2), 0) + 1
        list[LocationDto(1, 2)] = list.getOrDefault(LocationDto(1, 2), 0) + 1
        list[LocationDto(1, 2)] = list.getOrDefault(LocationDto(1, 2), 0) + 1
        list[LocationDto(1, 2)] = list.getOrDefault(LocationDto(1, 2), 0) + 1
        println(list)
    }

}
