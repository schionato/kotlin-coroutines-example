package com.examples.coroutines.adapter.ou.rest.geo.fallback

import com.example.coroutines.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.domain.Coordinates
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
    name = ["application.ports.output.geo.adapter"],
    havingValue = "fallback",
    matchIfMissing = false
)
class FindCityGeoRefFallbackAdapter : FindCityGeoRefOutputPort {

    override fun filteringBy(query: String): Coordinates {
        runBlocking { delay(250) }
        return Coordinates.empty()
    }

}