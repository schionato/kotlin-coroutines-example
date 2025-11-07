package com.example.coroutines.infra.adapter.`in`.rest

import com.example.coroutines.port.`in`.FetchUserDetailsInputPort
import com.example.coroutines.infra.adapter.`in`.rest.dto.CityDetailsResponse
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
    @param:Qualifier("sync") val fetchUserDetailsInputPortSync: FetchUserDetailsInputPort,
    @param:Qualifier("async") val fetchUserDetailsInputPortAsync: FetchUserDetailsInputPort,
    @param:Qualifier("parallel") val fetchUserDetailsInputPortParallel: FetchUserDetailsInputPort,
    @param:Qualifier("suspended") val fetchUserDetailsInputPortSuspended: FetchUserDetailsInputPort
) {

    @GetMapping
    fun fetch(
        @RequestParam("q") query: String,
        @RequestParam("method", defaultValue = "SYNC") fetchType: FetchType
    ): CityDetailsResponse {
        val found = when (fetchType) {
            FetchType.SYNC -> fetchUserDetailsInputPortSync.filteringBy(query)
            FetchType.ASYNC -> fetchUserDetailsInputPortAsync.filteringBy(query)
            FetchType.PARALLEL -> fetchUserDetailsInputPortParallel.filteringBy(query)
            FetchType.SUSPENDED -> fetchUserDetailsInputPortSuspended.filteringBy(query)
        }
        return CityDetailsResponse.of(found)
    }
}
