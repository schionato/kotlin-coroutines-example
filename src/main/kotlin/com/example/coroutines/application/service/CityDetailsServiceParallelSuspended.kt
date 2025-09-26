package com.example.coroutines.application.service

import com.example.coroutines.application.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.application.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.application.port.ou.FindCityNewsOutputPort
import com.example.coroutines.application.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.domain.CityDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("suspendedAndParallel")
class CityDetailsServiceParallelSuspended(
    private val findNewsOutput: FindCityNewsOutputPort,
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort
) : FetchUserDetailsInputPort {

    private val logger = LoggerFactory.getLogger(CityDetailsServiceParallelSuspended::class.java)

    override fun filteringBy(query: String): CityDetails = runBlocking {
        logger.info("finding city details using parallel - suspended service.")

        val weather = async(Dispatchers.IO) {
            fetchWeather(query)
        }

        val coordinates = async(Dispatchers.IO) {
            fetchGeoCoordinates(query)
        }

        val news = async(Dispatchers.IO) {
            fetchNews(query)
        }

        CityDetails.of(
            city = query,
            weather = weather.await(),
            coordinates = coordinates.await(),
            news = news.await()
        )
    }

    suspend fun fetchWeather(query: String) = findCityWeatherOutput.filteringBy(query)

    suspend fun fetchGeoCoordinates(query: String) = findCityGeoRefOutputPort.filteringBy(query)

    suspend fun fetchNews(query: String) = findNewsOutput.filteringBy(query)

}