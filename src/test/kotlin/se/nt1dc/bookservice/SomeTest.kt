package se.nt1dc.bookservice

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.math.floor
import kotlin.math.roundToInt

class SomeTest {
    @Test
    fun zxc() {
        println(task1(arrayOf("start", "connect", "message", "message", "end")))
    }

    fun task1(actions: Array<String>): Int {
        var connectionStatus = "disconnected"
        var messagesCount = 0
        var uncommittedMessageCount = 0
        for (action in actions) {
            when (action) {
                "start" -> {
                    uncommittedMessageCount = 0
                    connectionStatus = "connected"
                }

                "connect" -> {
                    if (connectionStatus == "connected") connectionStatus = "established"
                    else {
                        uncommittedMessageCount = 0
                        connectionStatus = "disconnected"
                    }
                }

                "message" -> {
                    if (connectionStatus == "established") uncommittedMessageCount++
                }

                "end" -> {
                    if (connectionStatus == "established") {
                        messagesCount += uncommittedMessageCount
                        uncommittedMessageCount = 0
                    }
                    connectionStatus = "disconnected"
                }
            }
        }
        return messagesCount
    }


    fun taskRewart(a: Int, b: Int): Int {
        if (b == 0) {
            return 1
        }
        var base = a % 10
        var exp = b % 4
        if (exp == 0) {
            exp = 4
        }
        var result = 1
        repeat(exp) {
            result *= base
        }
        return result % 10
    }

    /**
     * Implement function getResult
     */
    @Test
    fun rwe() {
        println(task2(3))
    }

    fun task2(n: Int): Int {
        if (n <= 2) {
            return 0
        }
        var s = 0.0
        for (i in 2..n) {
            for (j in 1 until i) {
                println("$j / $i =  ${j.toDouble() / i.toDouble()}")
                s += j.toDouble() / i.toDouble()
            }
        }
        return floor(s).toInt()
    }

    fun gcd(a: Int, b: Int): Int {
        if (b == 0) {
            return a
        }
        return gcd(b, a % b)
    }

    fun sumOfFractions(n: Int): Long {
        var sum: Long = 0
        for (denominator in 2..n) {
            for (numerator in 1 until denominator) {
                if (gcd(numerator, denominator) == 1) {
                    sum += numerator.toLong()
                }
            }
        }
        return sum
    }

    @Test
    fun asd() {
        println(sumOfFractions(3))
    }



}