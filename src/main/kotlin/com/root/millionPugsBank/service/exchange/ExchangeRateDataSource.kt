package com.root.millionPugsBank.service.exchange

interface ExchangeRateDataSource {
    fun retrieveLastExchangeRateForCurrency(currency: String): Double
}