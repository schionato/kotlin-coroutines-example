package com.example.coroutines.application.service

import com.example.coroutines.application.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.application.port.ou.FindCityCoordinatesOutput
import com.example.coroutines.application.port.ou.FindCityNewsOutput
import com.example.coroutines.application.port.ou.FindCityWeatherOutput
import com.example.coroutines.domain.CityDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("parallel")
class CityDetailsServiceParallel(
    private val findNewsOutput: FindCityNewsOutput,
    private val findCityWeatherOutput: FindCityWeatherOutput,
    private val findCityCoordinatesOutput: FindCityCoordinatesOutput
) : FetchUserDetailsInputPort {

    private val logger = LoggerFactory.getLogger(CityDetailsServiceParallel::class.java)

    override fun filteringBy(query: String): CityDetails = runBlocking {
        logger.info("finding city details using parallel service.")

        val weather = async(Dispatchers.IO) {
            findCityWeatherOutput.filteringBy(query)
        }

        val coordinates = async(Dispatchers.IO) {
            findCityCoordinatesOutput.filteringBy(query)
        }

        val news = async(Dispatchers.IO) {
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