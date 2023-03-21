package se.nt1dc.bookservice.dto

data class CreatePaymentOrderReq(
    var paymentSum: Double?,
    var accountId: Int
)