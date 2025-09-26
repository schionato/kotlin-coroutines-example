package com.example.coroutines.infra.adapter.ou.rest.news.newsdataio

import com.example.coroutines.application.port.ou.FindCityNewsOutputPort
import com.example.coroutines.domain.Article
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
@WhenNewsDataIsActive
class FindCityNewsDataIOAdapter(val newsDataIOAPI: NewsDataIOAPI) : FindCityNewsOutputPort {

    private val logger = LoggerFactory.getLogger(FindCityNewsDataIOAdapter::class.java)

    @CircuitBreaker(name = "fetch-news-by-city-circuit-breaker", fallbackMethod = "filteringByFallback")
    override fun filteringBy(query: String): List<Article> {
        val found = newsDataIOAPI.fetch(query)
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