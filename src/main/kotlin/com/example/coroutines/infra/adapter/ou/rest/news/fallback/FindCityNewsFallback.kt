package com.example.coroutines.infra.adapter.ou.rest.news.fallback

import com.example.coroutines.port.ou.FindCityNewsOutputPort
import com.example.coroutines.domain.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
    name = ["application.ports.output.news.adapter"],
    havingValue = "fallback",
    matchIfMissing = false
)
class FindCityNewsFallback : FindCityNewsOutputPort {

    override fun filteringBy(query: String): List<Article> {
        runBlocking {
            delay(1000)
        }
        return listOf()
    }

}