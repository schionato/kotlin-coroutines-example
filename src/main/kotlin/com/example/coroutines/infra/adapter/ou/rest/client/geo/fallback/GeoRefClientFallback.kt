package com.example.coroutines.infra.adapter.ou.rest.client.geo.fallback

import com.example.coroutines.infra.adapter.ou.rest.client.geo.GeoRefClient
import com.example.coroutines.infra.adapter.ou.rest.client.geo.GeoRefClientResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(
    name = ["external.geo.mode"],
    havingValue = "fallback",
    matchIfMissing = false
)
class GeoRefClientFallback : GeoRefClient {

    override fun fetch(query: String): List<GeoRefClientResult> {
        runBlocking { delay(250) }
        return emptyList()
    }

}