package com.example.coroutines.infra.adapter.ou.rest.client.geo.mominatim

import com.example.coroutines.domain.Coordinates
import com.example.coroutines.infra.adapter.ou.rest.client.geo.GeoRefClientResult
import com.fasterxml.jackson.annotation.JsonProperty

data class CoordinateResult(
    @field:JsonProperty("lat") val latitude: Double,
    @field:JsonProperty("lon") val longitude: Double
) : GeoRefClientResult {

    override fun toCoordinate() = Coordinates(latitude = latitude, longitude = longitude)

}