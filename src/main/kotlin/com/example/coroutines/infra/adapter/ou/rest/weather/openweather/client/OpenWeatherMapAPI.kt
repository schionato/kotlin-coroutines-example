package com.example.coroutines.infra.adapter.ou.rest.weather.openweather.client

import com.example.coroutines.infra.adapter.ou.rest.weather.openweather.WhenOpenWeatherIsActive
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "open-weather-map",
    url = "\${external.weather.openweather.url}",
    configuration = [OpenWeatherConfig::class]
)
@WhenOpenWeatherIsActive
interface OpenWeatherMapAPI {

    @GetMapping("?q={city}&units=metric")
    fun fetch(@PathVariable("city") query: String): OpenWeatherMapResult

}