package com.root.millionPugsBank.service.exchange.nbp

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate

@SpringBootTest
internal class NBPDataSourceTest @Autowired constructor(
    private final val restTemplate: RestTemplate
) {

    val nbpDataSource = NBPDataSource(restTemplate)

    @Test
    fun `should return more than 0 if data source works properly`() {
        val retrieveLastExchangeRateForCurrency = nbpDataSource.retrieveLastExchangeRateForCurrency("usd")
        assert(retrieveLastExchangeRateForCurrency>0.0)
    }
}