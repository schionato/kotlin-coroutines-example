package com.example.coroutines.infra.adapter.ou.rest.client.geo

import com.example.coroutines.domain.Coordinates
import com.fasterxml.jackson.annotation.JsonProperty

data class CoordinateResult(
    @field:JsonProperty("lat") val latitude: Double,
    @field:JsonProperty("lon") val longitude: Double
) {
    fun toCoordinate() = Coordinates(latitude = latitude, longitude = longitude)
}