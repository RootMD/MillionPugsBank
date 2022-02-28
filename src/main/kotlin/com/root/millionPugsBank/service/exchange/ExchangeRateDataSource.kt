package com.root.millionPugsBank.service.exchange

import com.root.millionPugsBank.service.exchange.nbp.NBPExchangeRateTable

interface ExchangeRateDataSource {

    fun retrieveLastExchangeData(): NBPExchangeRateTable

    fun retrieveLastExchangeRateForCurrency(currency: String): Double
}