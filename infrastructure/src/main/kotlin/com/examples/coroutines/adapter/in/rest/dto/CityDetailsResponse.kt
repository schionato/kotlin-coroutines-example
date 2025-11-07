package com.examples.coroutines.adapter.`in`.rest.dto

import com.example.coroutines.domain.Article
import com.example.coroutines.domain.CityDetails

data class CityDetailsResponse(
    val name: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val minTemperature: Double? = null,
    val maxTemperature: Double? = null,
    val wind: Double? = null,
    val news: List<CityArticle>? = listOf()
) {

    companion object {

        fun of(cityDetails: CityDetails): CityDetailsResponse {
            val (latitude, longitude) = cityDetails.coordinates
            val (minTemperature, maxTemperature, wind) = cityDetails.weather

            return CityDetailsResponse(
                name = cityDetails.name,
                latitude = latitude,
                longitude = longitude,
                wind = wind,
                minTemperature = minTemperature,
                maxTemperature = maxTemperature,
                news = CityArticle.ofAll(cityDetails.news)
            )
        }

    }

}

data class CityArticle(val title: String, val description: String, val href: String) {

    companion object {

        fun of(article: Article) = CityArticle(
            title = article.title,
            description = article.description,
            href = article.link
        )

        fun ofAll(news: List<Article>) = news.map { of(it) }.toList()
    }

}