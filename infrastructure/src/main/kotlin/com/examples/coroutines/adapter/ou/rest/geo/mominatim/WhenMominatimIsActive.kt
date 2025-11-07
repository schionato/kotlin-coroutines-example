package com.examples.coroutines.adapter.ou.rest.geo.mominatim

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

@ConditionalOnProperty(
    name = ["application.ports.output.geo.adapter"],
    havingValue = "mominatim",
    matchIfMissing = false
)
annotation class WhenMominatimIsActive
