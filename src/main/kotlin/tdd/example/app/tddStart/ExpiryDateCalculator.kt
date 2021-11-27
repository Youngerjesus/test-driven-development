package tdd.example.app.tddStart

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(billingDate: LocalDate, paymentAmount: Int): LocalDate {
        if (paymentAmount == 10000) {
            return billingDate.plusMonths(1)
        }
        throw Exception("금액이 맞지 않음")
    }

}