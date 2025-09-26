package com.example.coroutines.domain

data class Weather(val minTemperature: Double, val maxTemperature: Double, val wind: Double) {

    companion object {
        fun empty() = Weather(minTemperature = 0.0, maxTemperature = 0.0, wind = 0.0)
    }

}
