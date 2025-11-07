package com.example.coroutines.port.`in`

import com.example.coroutines.domain.CityDetails

fun interface FetchUserDetailsInputPort {

    fun filteringBy(query: String): CityDetails

}