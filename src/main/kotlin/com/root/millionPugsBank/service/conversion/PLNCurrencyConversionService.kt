package com.root.millionPugsBank.service.conversion

import com.root.millionPugsBank.service.exchange.ExchangeRateDataSource
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
@Primary
class PLNCurrencyConversionService(
    val exchangeRateDataSource: ExchangeRateDataSource
) : CurrencyConversionService {
    override fun convert(value: Double, currencyTo: String): Double {
        val bigDecimal = BigDecimal(value * exchangeRateDataSource.retrieveLastExchangeRateForCurrency(currencyTo))
        val scale = bigDecimal.setScale(2, RoundingMode.DOWN)
        return scale.toDouble()
    }
}