package se.nt1dc.bookservice.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import se.nt1dc.bookservice.ForeingServerRequestException
import se.nt1dc.bookservice.dto.BookWithCount
import se.nt1dc.bookservice.dto.ItemShippingRequest
import se.nt1dc.bookservice.dto.LocationDto
import se.nt1dc.bookservice.dto.OrderDto
import se.nt1dc.bookservice.repository.BookRepository

@Service
@Transactional
class ShippingServiceImpl(
    val bookRepository: BookRepository,
    val requestSender: RequestSender,
) : ShippingService {

    override fun calculateShipping(orderDto: OrderDto): Double? {
        val itemShipRequest = orderDto.bookOrderList.stream().map { book ->
            BookWithCount(bookRepository.findById(book.bookId).orElseThrow { throw RuntimeException() }, book.count)
        }.map { bookWithCount ->
            if (bookWithCount.book.items.size < bookWithCount.count) throw RuntimeException(
                bookWithCount.book.name + " доступно только " + bookWithCount.book.items.size + " из" + bookWithCount.count
            )
            bookWithCount.book.items.stream().limit(bookWithCount.count.toLong())
                .map {
                    ItemShippingRequest(
                        LocationDto(it.stock.location.latitude, it.stock.location.longitude),
                        orderDto.to,
                        it.book.length,
                        it.book.width,
                        it.book.height,
                        it.book.weight
                    )
                }
        }.toList()
        val shipPriceSumResponse = requestSender.sendReq("delivery-service/calculate", itemShipRequest)
        if (shipPriceSumResponse.statusCode.is2xxSuccessful) return shipPriceSumResponse.body as Double
        throw ForeingServerRequestException(shipPriceSumResponse)
    }
}