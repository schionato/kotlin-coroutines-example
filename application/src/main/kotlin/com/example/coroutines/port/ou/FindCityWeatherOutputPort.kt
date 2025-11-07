package com.example.coroutines.port.ou

import com.example.coroutines.domain.Weather

fun interface FindCityWeatherOutputPort {

    fun filteringBy(query: String): Weather

}