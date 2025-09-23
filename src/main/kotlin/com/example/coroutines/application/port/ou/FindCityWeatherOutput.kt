package com.example.coroutines.application.port.ou

import com.example.coroutines.domain.Weather

interface FindCityWeatherOutput {

    fun filteringBy(query: String): Weather

}