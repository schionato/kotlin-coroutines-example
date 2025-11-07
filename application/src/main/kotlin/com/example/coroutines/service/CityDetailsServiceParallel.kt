package com.example.coroutines.service

import com.example.coroutines.domain.CityDetails
import com.example.coroutines.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.port.ou.FindCityNewsOutputPort
import com.example.coroutines.port.ou.FindCityWeatherOutputPort
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class CityDetailsServiceParallel(
    private val findNewsOutput: FindCityNewsOutputPort,
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FetchUserDetailsInputPort {

    override fun filteringBy(query: String): CityDetails = runBlocking {
        val weather = async(dispatcher) {
            findCityWeatherOutput.filteringBy(query)
        }

        val coordinates = async(dispatcher) {
            findCityGeoRefOutputPort.filteringBy(query)
        }

        val news = async(dispatcher) {
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