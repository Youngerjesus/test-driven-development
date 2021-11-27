package tdd.example.app.tddStart

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(paymentData: PaymentData): LocalDate {
        var paymentAmount = paymentData.paymentAmount
        val addedYears = paymentAmount.div(100000)
        if (addedYears > 0) paymentAmount -= addedYears.times(100000)

        val addedMonths = paymentAmount.div(10000)
        val result = paymentData.billingDate
                        .plusYears(addedYears.toLong())
                        .plusMonths(addedMonths.toLong())

        if (paymentData.firstBillingDate.dayOfMonth != paymentData.billingDate.dayOfMonth) {
            if (result.month.maxLength() != paymentData.firstBillingDate.dayOfMonth) {
                return result.withDayOfMonth(result.month.maxLength())
            }
            return result.withDayOfMonth(paymentData.firstBillingDate.dayOfMonth)
        }
        return result
    }
}