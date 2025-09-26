package com.example.coroutines.application.service

import com.example.coroutines.application.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.application.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.application.port.ou.FindCityNewsOutputPort
import com.example.coroutines.application.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.domain.CityDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("async")
class CityDetailsServiceAsync(
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort,
    private val findNewsOutput: FindCityNewsOutputPort
) : FetchUserDetailsInputPort {

    private val logger = LoggerFactory.getLogger(CityDetailsServiceAsync::class.java)

    override fun filteringBy(query: String): CityDetails = runBlocking {
        logger.info("finding city details using async service.")

        val weather = async {
            findCityWeatherOutput.filteringBy(query)
        }

        val coordinates = async {
            findCityGeoRefOutputPort.filteringBy(query)
        }

        val news = async {
            findNewsOutput.filteringBy(query)
        }

        CityDetails.of(
            city = query,
            weather = weather.await(),
            coordinates = coordinates.await(),
            news = news.await()
        )
    }

}