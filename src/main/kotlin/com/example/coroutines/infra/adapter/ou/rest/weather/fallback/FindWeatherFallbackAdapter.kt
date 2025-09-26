package com.example.coroutines.infra.adapter.ou.rest.weather.fallback

import com.example.coroutines.application.port.ou.FindCityWeatherOutputPort
import com.example.coroutines.domain.Weather
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
    name = ["application.ports.output.weather.adapter"],
    havingValue = "fallback",
    matchIfMissing = false
)
class FindWeatherFallbackAdapter : FindCityWeatherOutputPort {

    override fun filteringBy(query: String): Weather {
        runBlocking { delay(725) }
        return Weather.empty()
    }
}