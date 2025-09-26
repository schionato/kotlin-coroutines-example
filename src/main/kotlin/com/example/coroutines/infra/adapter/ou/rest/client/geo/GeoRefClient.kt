package com.example.coroutines.infra.adapter.ou.rest.client.geo

interface GeoRefClient {

    fun fetch(query: String): List<GeoRefClientResult>

}