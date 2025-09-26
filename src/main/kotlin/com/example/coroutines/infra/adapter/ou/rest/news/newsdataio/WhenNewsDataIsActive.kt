package com.example.coroutines.infra.adapter.ou.rest.news.newsdataio

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

@ConditionalOnProperty(
    name = ["application.ports.output.news.adapter"],
    havingValue = "newsdataio",
    matchIfMissing = false
)
annotation class WhenNewsDataIsActive
