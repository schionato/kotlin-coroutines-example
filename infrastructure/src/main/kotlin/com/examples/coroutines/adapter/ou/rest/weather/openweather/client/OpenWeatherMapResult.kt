package com.examples.coroutines.adapter.ou.rest.weather.openweather.client

import com.example.coroutines.domain.Weather
import com.fasterxml.jackson.annotation.JsonProperty

data class Wind(val speed: Double)

data class Main(
    val temp: Double,
    @field:JsonProperty("temp_min") val tempMin: Double,
    @field:JsonProperty("temp_max") val tempMax: Double
)

class OpenWeatherMapResult(val name: String, val main: Main, val wind: Wind) {

    fun toWeather() = Weather(
        minTemperature = main.tempMin,
        maxTemperature = main.tempMax,
        wind = wind.speed
    )

}
