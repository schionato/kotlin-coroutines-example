package com.example.coroutines.service

import com.example.coroutines.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.port.ou.FindCityNewsOutputPort
import com.example.coroutines.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.domain.CityDetails
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("sync")
class CityDetailsServiceSync(
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort,
    private val findNewsOutput: FindCityNewsOutputPort
) : FetchUserDetailsInputPort {

    private val logger = LoggerFactory.getLogger(CityDetailsServiceSync::class.java)

    override fun filteringBy(query: String): CityDetails {
        logger.info("finding city details using sync service.")

        val weather = findCityWeatherOutput.filteringBy(query)
        val coordinates = findCityGeoRefOutputPort.filteringBy(query)
        val news = findNewsOutput.filteringBy(query)

        return CityDetails.of(
            city = query,
            weather = weather,
            coordinates = coordinates,
            news = news
        )
    }

}