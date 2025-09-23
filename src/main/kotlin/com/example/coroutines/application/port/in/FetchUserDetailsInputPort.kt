package com.example.coroutines.application.port.`in`

import com.example.coroutines.domain.CityDetails

interface FetchUserDetailsInputPort {

    fun filteringBy(query: String): CityDetails

}