package com.example.coroutines.infra.adapter.ou.rest.geo.mominatim.client

import com.example.coroutines.infra.adapter.ou.rest.geo.mominatim.WhenMominatimIsActive
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "mominatim-api-client",
    url = "\${external.geo.nominatim.url}"
)
@WhenMominatimIsActive
interface MominatimAPI {

    @GetMapping("/search?q={city}&format=json")
    fun fetch(@PathVariable("city") query: String): List<CoordinateResult>

}