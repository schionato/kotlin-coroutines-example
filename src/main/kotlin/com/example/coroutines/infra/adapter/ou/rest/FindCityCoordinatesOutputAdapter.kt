package com.example.coroutines.infra.adapter.ou.rest

import com.example.coroutines.application.port.ou.FindCityCoordinatesOutput
import com.example.coroutines.domain.Coordinates
import com.example.coroutines.infra.adapter.ou.rest.client.geo.MominatimAPI
import org.springframework.stereotype.Repository

@Repository
class FindCityCoordinatesOutputAdapter(val mominatimAPI: MominatimAPI) : FindCityCoordinatesOutput {

    override fun filteringBy(query: String): Coordinates {
        val found = mominatimAPI.fetch(query)
        if (found.isEmpty())
            throw IllegalArgumentException()
        return found[0].toCoordinate()
    }
}