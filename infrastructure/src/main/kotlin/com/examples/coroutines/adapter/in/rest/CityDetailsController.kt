package com.examples.coroutines.adapter.`in`.rest

import com.example.coroutines.port.`in`.GetCityDetailsInputPort
import com.examples.coroutines.adapter.`in`.rest.dto.CityDetailsResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

enum class FetchType {
    SYNC,
    ASYNC,
    SUSPENDED,
    PARALLEL
}

@RestController
@RequestMapping("/cities")
class CityDetailsController(
    @param:Qualifier("sync") val getCityDetailsInputPortSync: GetCityDetailsInputPort,
    @param:Qualifier("async") val getCityDetailsInputPortAsync: GetCityDetailsInputPort,
    @param:Qualifier("parallel") val getCityDetailsInputPortParallel: GetCityDetailsInputPort,
    @param:Qualifier("suspended") val getCityDetailsInputPortSuspended: GetCityDetailsInputPort
) {

    @GetMapping
    fun fetch(
        @RequestParam("q") query: String,
        @RequestParam("method", defaultValue = "SYNC") fetchType: FetchType
    ): CityDetailsResponse {
        val found = when (fetchType) {
            FetchType.SYNC -> getCityDetailsInputPortSync.filteringBy(query)
            FetchType.ASYNC -> getCityDetailsInputPortAsync.filteringBy(query)
            FetchType.PARALLEL -> getCityDetailsInputPortParallel.filteringBy(query)
            FetchType.SUSPENDED -> getCityDetailsInputPortSuspended.filteringBy(query)
        }
        return CityDetailsResponse.of(found)
    }
}
