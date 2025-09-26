package com.example.coroutines.infra.adapter.ou.rest.client.geo

import com.example.coroutines.domain.Coordinates

interface GeoRefClientResult {
    fun toCoordinate(): Coordinates
}
