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

    private fun assertExpiryDate(billingDate: LocalDate, paymentAmount: Int, expiryDate: LocalDate) {
        val expectedExpiryDate: LocalDate = expiryDateCalculator.calculateExpiryDate(billingDate, paymentAmount)
        assertEquals(expectedExpiryDate, expiryDate)
    }

}
