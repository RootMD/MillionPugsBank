package com.root.millionPugsBank.service.exchange.nbp

import java.time.ZonedDateTime

data class NBPExchangeRateTable(
    val tradingDate: ZonedDateTime,
    val effectiveDate: ZonedDateTime,
    val rateTables: List<RateTable>
)
