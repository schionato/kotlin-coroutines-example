package com.example.coroutines.infra.adapter.ou.rest.client.geo.mominatim

import com.example.coroutines.infra.adapter.ou.rest.DisableCache
import com.example.coroutines.infra.adapter.ou.rest.client.geo.GeoRefClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@ConditionalOnProperty(
    name = ["external.geo.mode"],
    havingValue = "nominatim",
    matchIfMissing = false
)
@FeignClient(
    name = "mominatim-api-client",
    url = "\${external.geo.nominatim.url}"
)
interface MominatimAPI : GeoRefClient {

    @DisableCache
    @GetMapping("/search?q={city}&format=json")
    override fun fetch(@PathVariable("city") query: String): List<CoordinateResult>

}