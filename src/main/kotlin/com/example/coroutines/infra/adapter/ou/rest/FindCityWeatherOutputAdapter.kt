package com.example.coroutines.infra.adapter.ou.rest

import com.example.coroutines.application.port.ou.FindCityWeatherOutput
import com.example.coroutines.domain.Weather
import com.example.coroutines.infra.adapter.ou.rest.client.weather.OpenWeatherMap
import org.springframework.stereotype.Repository

@Repository
class FindCityWeatherOutputAdapter(val openWeatherMap: OpenWeatherMap) : FindCityWeatherOutput {

    override fun filteringBy(query: String): Weather {
        val found = openWeatherMap.fetch(query)
        return Weather(
            minTemperature = found.main.tempMin,
            maxTemperature = found.main.tempMax,
            wind = found.wind.speed
        )
    }

}