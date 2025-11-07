package com.example.coroutines.port.ou

import com.example.coroutines.domain.Coordinates

fun interface FindCityGeoRefOutputPort {

    fun filteringBy(query: String): Coordinates

}