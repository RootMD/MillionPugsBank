package com.root.millionPugsBank.service.exchange.nbp

import com.root.millionPugsBank.service.exchange.ExchangeRateDataSource
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Service
@Primary
class NBPDataSource(
    val restTemplate: RestTemplate
) : ExchangeRateDataSource {
    val nbpRatesUrl = "http://api.nbp.pl/api/exchangerates/"
    override fun retrieveLastExchangeData(): NBPExchangeRateTable {
        return restTemplate.getForObject(
            "${nbpRatesUrl}tables/c?format=json",
            NBPExchangeRateTable::class.java
        )
            ?: throw RestClientException("Call to NBP servers failed, Couldn't retrieve last exchange rates")
    }

    override fun retrieveLastExchangeRateForCurrency(currency: String): Double {
        val response = restTemplate.getForObject(
            "${nbpRatesUrl}rates/c/$currency?format=json",
            NBPExchangeRateSingle::class.java
        ) ?: throw RestClientException("Call to NBP servers failed, Couldn't retrieve last exchange rates")
        return response.rates[0].bid
    }
}