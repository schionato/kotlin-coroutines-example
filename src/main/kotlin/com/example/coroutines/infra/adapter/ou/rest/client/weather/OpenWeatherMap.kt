package com.example.coroutines.infra.adapter.ou.rest.client.weather

import com.example.coroutines.infra.adapter.ou.rest.DisableCache
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "open-weather-map",
    url = "\${external.weather.url}",
    configuration = [OpenWeatherConfig::class]
)
interface OpenWeatherMap {

    @DisableCache
    @GetMapping("?q={city}&units=metric")
    fun fetch(@PathVariable("city") query: String): OpenWeatherMapResult

}