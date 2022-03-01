package com.root.millionPugsBank.service.conversion

import com.root.millionPugsBank.service.exchange.ExchangeRateDataSource
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class PLNCurrencyConversionServiceTest {

    @Test
    fun `should return 10 when converting 2 for value of 5`() {
        val nbpDataSource: ExchangeRateDataSource = mockk()
        every { nbpDataSource.retrieveLastExchangeRateForCurrency("EUR") } returns 5.0
        val plnCurrencyConversionService = PLNCurrencyConversionService(nbpDataSource)

        val convert = plnCurrencyConversionService.convert(2.0, "EUR")

        assert(convert == 10.0)

    }
}