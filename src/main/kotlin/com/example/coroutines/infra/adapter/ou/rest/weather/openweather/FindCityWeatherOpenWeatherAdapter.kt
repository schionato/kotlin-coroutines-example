package com.example.coroutines.infra.adapter.ou.rest.weather.openweather

import com.example.coroutines.application.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.infra.adapter.ou.rest.weather.openweather.client.OpenWeatherMapAPI
import org.springframework.stereotype.Repository

@Repository
@WhenOpenWeatherIsActive
class FindCityWeatherOpenWeatherAdapter(val weatherClient: OpenWeatherMapAPI) : FindCityWeatherOutputPort {

    override fun filteringBy(query: String) = weatherClient.fetch(query).toWeather()

}