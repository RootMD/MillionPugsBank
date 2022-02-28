package com.root.millionPugsBank.service.exchange.nbp

data class RateTable(
    val currencyName: String,
    val code: String,
    val bid: Double,
    val ask: Double
)
