package com.example.coroutines.infra.adapter.ou.rest

import com.example.coroutines.application.port.ou.FindCityNewsOutput
import com.example.coroutines.domain.Article
import com.example.coroutines.infra.adapter.ou.rest.client.news.NewsClient
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class FindCityNewsOutputAdapter(val newsClient: NewsClient) : FindCityNewsOutput {

    private val logger = LoggerFactory.getLogger(FindCityNewsOutputAdapter::class.java)

    @CircuitBreaker(name = "fetch-news-by-city-circuit-breaker", fallbackMethod = "filteringByFallback")
    override fun filteringBy(query: String): List<Article> {
        val found = newsClient.fetch(query)
        return found.results.map { r ->
            Article(title = r.title, description = r.description, link = r.link)
        }.toList()
    }

    fun filteringByFallback(query: String, t: Throwable): List<Article> {
        logger.warn("using fallback to retrieve news for $query")
        logger.error(t.message)
        return listOf()
    }

}