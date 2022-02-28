package com.root.millionPugsBank.service.exchange.nbp

data class NBPExchangeRateSingle(
    val currency: String,
    val code: String,
    val rates: List<RateSingle>
)
