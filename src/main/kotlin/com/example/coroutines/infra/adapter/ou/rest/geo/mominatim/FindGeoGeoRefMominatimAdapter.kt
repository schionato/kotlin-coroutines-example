package com.example.coroutines.infra.adapter.ou.rest.geo.mominatim

import com.example.coroutines.application.port.ou.FindCityGeoRefOutputPort
import com.example.coroutines.domain.Coordinates
import com.example.coroutines.infra.adapter.ou.rest.geo.mominatim.client.MominatimAPI
import org.springframework.stereotype.Repository

@Repository
@WhenMominatimIsActive
class FindGeoGeoRefMominatimAdapter(val geoRefClient: MominatimAPI) : FindCityGeoRefOutputPort {

    override fun filteringBy(query: String): Coordinates {
        val found = geoRefClient.fetch(query)
        if (found.isEmpty())
            return Coordinates.Companion.empty()
        return found[0].toCoordinate()
    }
}