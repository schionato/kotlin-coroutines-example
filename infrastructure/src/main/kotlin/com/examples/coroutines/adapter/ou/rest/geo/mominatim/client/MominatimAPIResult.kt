package com.examples.coroutines.adapter.ou.rest.geo.mominatim.client

import com.example.coroutines.domain.Coordinates
import com.fasterxml.jackson.annotation.JsonProperty

data class CoordinateResult(
    @field:JsonProperty("lat") val latitude: Double,
    @field:JsonProperty("lon") val longitude: Double
) {

    fun toCoordinate() = Coordinates(latitude = latitude, longitude = longitude)

}