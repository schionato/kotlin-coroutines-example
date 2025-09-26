package com.example.coroutines.application.service

import com.example.coroutines.application.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.application.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.application.port.ou.FindCityNewsOutputPort
import com.example.coroutines.application.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.domain.CityDetails
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("suspended")
class CityDetailsServiceSuspended(
    private val findNewsOutput: FindCityNewsOutputPort,
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort
) : FetchUserDetailsInputPort {

    private val logger = LoggerFactory.getLogger(CityDetailsServiceSuspended::class.java)

    override fun filteringBy(query: String): CityDetails = runBlocking {
        logger.info("finding city details using parallel - suspended service.")

        val weather = fetchWeather(query)

        val coordinates = fetchGeoCoordinates(query)

        val news = fetchNews(query)

        CityDetails.of(
            city = query,
            weather = weather,
            coordinates = coordinates,
            news = news
        )
    }

    suspend fun fetchWeather(query: String) = findCityWeatherOutput.filteringBy(query)

    suspend fun fetchGeoCoordinates(query: String) = findCityGeoRefOutputPort.filteringBy(query)

    suspend fun fetchNews(query: String) = findNewsOutput.filteringBy(query)
}