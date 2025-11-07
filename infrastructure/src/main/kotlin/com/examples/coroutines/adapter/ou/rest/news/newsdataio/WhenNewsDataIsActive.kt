package com.examples.coroutines.adapter.ou.rest.news.newsdataio

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

@ConditionalOnProperty(
    name = ["application.ports.output.news.adapter"],
    havingValue = "newsdataio",
    matchIfMissing = false
)
annotation class WhenNewsDataIsActive
