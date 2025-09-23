package com.example.coroutines.infra.adapter.ou.rest.client.geo

import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "mominatim-api-client", url = "https://nominatim.openstreetmap.org/")
interface MominatimAPI {

    @Headers(
        "Cache-Control: no-cache, no-store, must-revalidate",
        "Pragma: no-cache",
        "Expires: 0"
    )
    @GetMapping("/search?q={city}&format=json")
    fun fetch(@PathVariable("city") query: String): List<CoordinateResult>

}