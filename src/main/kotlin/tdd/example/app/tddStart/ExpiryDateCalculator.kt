package tdd.example.app.tddStart

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(paymentData: PaymentData): LocalDate {
        val addedMonths = paymentData.paymentAmount / 10000
        val result = paymentData.billingDate.plusMonths(addedMonths.toLong())

        if (paymentData.firstBillingDate.dayOfMonth != paymentData.billingDate.dayOfMonth) {
            if (result.month.maxLength() != paymentData.firstBillingDate.dayOfMonth) {
                return result.withDayOfMonth(result.month.maxLength())
            }
            return result.withDayOfMonth(paymentData.firstBillingDate.dayOfMonth)
        }
        return result
    }
}