package com.example.coroutines.infra.adapter.ou.rest.client.weather

import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "open-weather-map", url = "\${external.weather.url}", configuration = [OpenWeatherConfig::class])
interface OpenWeatherMap {

    @Headers(
        "Cache-Control: no-cache, no-store, must-revalidate",
        "Pragma: no-cache",
        "Expires: 0"
    )
    @GetMapping("?q={city}&units=metric")
    fun fetch(@PathVariable("city") query: String): OpenWeatherMapResult

}