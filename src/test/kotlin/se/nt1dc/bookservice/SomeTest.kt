package se.nt1dc.bookservice

import org.junit.jupiter.api.Test
import java.util.stream.Collectors

class SomeTest {
    @Test
    fun zxc() {
        val groupBy = listOf<Int>(1, 2, 2, 3, 4).groupingBy {it }.eachCount()
        println(groupBy)

    }
}