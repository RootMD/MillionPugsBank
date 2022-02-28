package com.root.millionPugsBank.service.conversion

interface CurrencyConversionService {

    fun convert(value: Double, currencyTo: String): Double
}