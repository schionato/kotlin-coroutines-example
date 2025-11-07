package com.examples.coroutines.spring

import com.example.coroutines.port.`in`.GetCityDetailsInputPort
import com.example.coroutines.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.port.ou.FindCityNewsOutputPort
import com.example.coroutines.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.usecase.GetCityDetailsAsyncUseCase
import com.example.coroutines.usecase.GetCityDetailsParallelUseCase
import com.example.coroutines.usecase.GetCityDetailsSuspendedUseCase
import com.example.coroutines.usecase.GetCityDetailsSyncUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FetchUserDetailsConfig (
    private val findCityWeatherOutput: FindCityWeatherOutputPort,
    private val findCityGeoRefOutputPort: FindCityGeoRefOutputPort,
    private val findNewsOutput: FindCityNewsOutputPort
) {

    @Bean("async")
    fun async() : GetCityDetailsInputPort = GetCityDetailsAsyncUseCase(
        findCityWeatherOutput = findCityWeatherOutput,
        findCityGeoRefOutputPort = findCityGeoRefOutputPort,
        findNewsOutput = findNewsOutput
    )

    @Bean("sync")
    fun sync() : GetCityDetailsInputPort = GetCityDetailsSyncUseCase(
        findCityWeatherOutput = findCityWeatherOutput,
        findCityGeoRefOutputPort = findCityGeoRefOutputPort,
        findNewsOutput = findNewsOutput
    )

    @Bean("parallel")
    fun parallel() : GetCityDetailsInputPort = GetCityDetailsParallelUseCase(
        findCityWeatherOutput = findCityWeatherOutput,
        findCityGeoRefOutputPort = findCityGeoRefOutputPort,
        findNewsOutput = findNewsOutput
    )

    @Bean("suspended")
    fun suspended() : GetCityDetailsInputPort = GetCityDetailsSuspendedUseCase(
        findCityWeatherOutput = findCityWeatherOutput,
        findCityGeoRefOutputPort = findCityGeoRefOutputPort,
        findNewsOutput = findNewsOutput
    )

}