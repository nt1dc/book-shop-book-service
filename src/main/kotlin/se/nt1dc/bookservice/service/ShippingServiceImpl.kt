package se.nt1dc.bookservice.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import se.nt1dc.bookservice.dao.BookWithCount
import se.nt1dc.bookservice.dto.ItemShippingRequest
import se.nt1dc.bookservice.dto.OrderDto
import se.nt1dc.bookservice.repository.BookRepository

@Service
@Transactional()
class ShippingServiceImpl(val bookRepository: BookRepository, val requestSender: RequestSender) : ShippingService {
    override fun calculateShipping(orderDto: OrderDto): String? {
        val books = orderDto.bookOrderList.stream()
            .map { book -> BookWithCount(bookRepository.findByName(book.bookName), book.count) }.toList()
        val locationWithSize = HashMap<String, Int>()
        for (bookReq in books) {
            var c = bookReq.count
            for (item in bookReq.book.items.sortedBy { it.count }) {
                if (item.count > 0) {
                    val tmp = c
                    if (item.count >= c) {
                        item.count -= c
                        c = 0
                    } else {
                        c -= item.count
                        item.count = 0
                    }
                    locationWithSize[item.stock.location] = locationWithSize.getOrDefault(item.stock.location, 0)
                    +item.book.height * item.book.width * item.book.length * tmp - c
                }
                if (c == 0) break
            }
            if (c > 0) throw Exception(bookReq.book.name + " доступно только " + (bookReq.count - c) + " из" + bookReq.count)
        }
        var itemShippingList =
            locationWithSize.map { m -> ItemShippingRequest(m.key, orderDto.destinationAddress, m.value) }.toList()
        val sendReq = requestSender.sendReq("delivery-service", itemShippingList)
        return sendReq.body
    }
}