package com.example.coroutines.service

import com.example.coroutines.domain.CityDetails
import com.example.coroutines.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.port.ou.FindCityNewsOutputPort
import com.example.coroutines.port.ou.FindCityWeatherOutputPort
import kotlinx.coroutines.runBlocking

class CityDetailsServiceSuspended(
    private val findNewsOutput: FindCityNewsOutputPort,
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort
) : FetchUserDetailsInputPort {

    override fun filteringBy(query: String): CityDetails = runBlocking {
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

    fun fetchWeather(query: String) = findCityWeatherOutput.filteringBy(query)

    fun fetchGeoCoordinates(query: String) = findCityGeoRefOutputPort.filteringBy(query)

    fun fetchNews(query: String) = findNewsOutput.filteringBy(query)
}