package se.nt1dc.bookservice.dto.payment

data class CreatePaymentOrderReq(
    var paymentSum: Double?,
    var accountId: Int
)