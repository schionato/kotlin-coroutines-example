package com.example.coroutines.application.port.ou

import com.example.coroutines.domain.Weather

interface FindCityWeatherOutputPort {

    fun filteringBy(query: String): Weather

}