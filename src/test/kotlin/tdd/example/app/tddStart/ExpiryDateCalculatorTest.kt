package tdd.example.app.tddStart

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

internal class ExpiryDateCalculatorTest {
    private val expiryDateCalculator = ExpiryDateCalculator()

    @Test fun 만원_납부하면_한달_뒤_만료일이_만료됨() {
        assertExpiryDate(
            LocalDate.of(2021, 1, 1), 10000,
            LocalDate.of(2021,2,1)
        )

        assertExpiryDate(
            LocalDate.of(2021, 5, 5), 10000,
            LocalDate.of(2021,6,5)
        )

        assertExpiryDate(
            LocalDate.of(2021,1,31), 10000,
            LocalDate.of(2021,2,28)
        )

        assertExpiryDate(
            LocalDate.of(2021,1,28), 10000,
            LocalDate.of(2021,2,28)
        )
    }

    @Test fun 만원_납부한_후_만료되고_또_만원납부_한_경우() {
        assertExpiryDate(
            PaymentData(
                LocalDate.of(2021, 1, 31),
                LocalDate.of(2021, 2, 28),
                20000
            ),
            LocalDate.of(2021,4,30)
        )

        assertExpiryDate(
            PaymentData(
                LocalDate.of(2021, 2, 28),
                LocalDate.of(2021, 3, 31),
                20000,
            ),
            LocalDate.of(2021,5,31)
        )
    }

    private fun assertExpiryDate(paymentData: PaymentData, expiryDate: LocalDate) {
        val expectedExpiryDate = expiryDateCalculator.calculateExpiryDate(paymentData)
        assertEquals(expectedExpiryDate, expiryDate)
    }

    private fun assertExpiryDate(billingDate: LocalDate, paymentAmount: Int, expiryDate: LocalDate) {
        val paymentData = PaymentData(billingDate, billingDate, paymentAmount)
        val expectedExpiryDate: LocalDate = expiryDateCalculator.calculateExpiryDate(paymentData)
        assertEquals(expectedExpiryDate, expiryDate)
    }

}
