package com.example.coroutines.usecase

import com.example.coroutines.domain.CityDetails
import com.example.coroutines.port.`in`.GetCityDetailsInputPort
import com.example.coroutines.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.port.ou.FindCityNewsOutputPort
import com.example.coroutines.port.ou.FindCityWeatherOutputPort

class GetCityDetailsSyncUseCase(
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort,
    private val findNewsOutput: FindCityNewsOutputPort
) : GetCityDetailsInputPort {

    override fun filteringBy(query: String): CityDetails {
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