package com.example.coroutines.infra.adapter.ou.rest.client.weather

import com.fasterxml.jackson.annotation.JsonProperty

data class Wind(val speed: Double)

data class Main(
    val temp: Double,
    @field:JsonProperty("temp_min") val tempMin: Double,
    @field:JsonProperty("temp_max") val tempMax: Double
)

data class OpenWeatherMapResult(val name: String, val main: Main, val wind: Wind)
