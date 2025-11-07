package com.example.coroutines.service

import com.example.coroutines.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.port.ou.FindCityNewsOutputPort
import com.example.coroutines.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.domain.CityDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class CityDetailsServiceAsync(
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort,
    private val findNewsOutput: FindCityNewsOutputPort
) : FetchUserDetailsInputPort {

    override fun filteringBy(query: String): CityDetails = runBlocking {
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