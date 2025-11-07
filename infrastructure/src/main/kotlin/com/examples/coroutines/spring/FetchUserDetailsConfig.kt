package com.examples.coroutines.spring

import com.example.coroutines.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.port.ou.FindCityNewsOutputPort
import com.example.coroutines.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.service.CityDetailsServiceAsync
import com.example.coroutines.service.CityDetailsServiceParallel
import com.example.coroutines.service.CityDetailsServiceSuspended
import com.example.coroutines.service.CityDetailsServiceSync
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FetchUserDetailsConfig (
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort,
    private val findNewsOutput: FindCityNewsOutputPort
) {

    @Bean("async")
    fun async() : FetchUserDetailsInputPort = CityDetailsServiceAsync(
        findCityWeatherOutput = findCityWeatherOutput,
        findCityGeoRefOutputPort = findCityGeoRefOutputPort,
        findNewsOutput = findNewsOutput
    )

    @Bean("sync")
    fun sync() : FetchUserDetailsInputPort = CityDetailsServiceSync(
        findCityWeatherOutput = findCityWeatherOutput,
        findCityGeoRefOutputPort = findCityGeoRefOutputPort,
        findNewsOutput = findNewsOutput
    )

    @Bean("parallel")
    fun parallel() : FetchUserDetailsInputPort = CityDetailsServiceParallel(
        findCityWeatherOutput = findCityWeatherOutput,
        findCityGeoRefOutputPort = findCityGeoRefOutputPort,
        findNewsOutput = findNewsOutput
    )

    @Bean("suspended")
    fun suspended() : FetchUserDetailsInputPort = CityDetailsServiceSuspended(
        findCityWeatherOutput = findCityWeatherOutput,
        findCityGeoRefOutputPort = findCityGeoRefOutputPort,
        findNewsOutput = findNewsOutput
    )

}