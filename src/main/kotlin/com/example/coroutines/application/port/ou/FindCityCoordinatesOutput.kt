package com.example.coroutines.application.port.ou

import com.example.coroutines.domain.Coordinates

interface FindCityCoordinatesOutput {

    fun filteringBy(query: String): Coordinates

}