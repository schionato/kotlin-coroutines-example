package com.example.coroutines.application.service

import com.example.coroutines.application.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.application.port.ou.FindCityCoordinatesOutput
import com.example.coroutines.application.port.ou.FindCityNewsOutput
import com.example.coroutines.application.port.ou.FindCityWeatherOutput
import com.example.coroutines.domain.CityDetails
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("sync")
class CityDetailsServiceSync(
    private val findCityWeatherOutput: FindCityWeatherOutput,
    private val findCityCoordinatesOutput: FindCityCoordinatesOutput,
    private val findNewsOutput: FindCityNewsOutput
) : FetchUserDetailsInputPort {

    private val logger = LoggerFactory.getLogger(CityDetailsServiceSync::class.java)

    override fun filteringBy(query: String): CityDetails {
        logger.info("finding city details using sync service.")

        val weather = findCityWeatherOutput.filteringBy(query)
        val coordinates = findCityCoordinatesOutput.filteringBy(query)
        val news = findNewsOutput.filteringBy(query)

        return CityDetails.of(
            city = query,
            weather = weather,
            coordinates = coordinates,
            news = news
        )
    }

}