package com.examples.coroutines.adapter.ou.rest.weather.openweather

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

@ConditionalOnProperty(
    name = ["application.ports.output.weather.adapter"],
    havingValue = "openweather",
    matchIfMissing = false
)
annotation class WhenOpenWeatherIsActive
