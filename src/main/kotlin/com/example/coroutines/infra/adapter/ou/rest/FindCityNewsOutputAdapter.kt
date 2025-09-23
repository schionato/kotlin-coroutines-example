package com.example.coroutines.infra.adapter.ou.rest

import com.example.coroutines.application.port.ou.FindCityNewsOutput
import com.example.coroutines.domain.Article
import com.example.coroutines.infra.adapter.ou.rest.client.news.NewsClient
import org.springframework.stereotype.Repository

@Repository
class FindCityNewsOutputAdapter(val newsCl: NewsClient) : FindCityNewsOutput {

    override fun filteringBy(query: String): List<Article> {
        val found = newsCl.fetch(query)
        return found.results.map { r ->
            Article(title = r.title, description = r.description, link = r.link)
        }.toList()
    }

}