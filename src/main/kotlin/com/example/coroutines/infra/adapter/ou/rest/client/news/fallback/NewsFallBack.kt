package com.example.coroutines.infra.adapter.ou.rest.client.news.fallback

import com.example.coroutines.infra.adapter.ou.rest.client.news.NewsClient
import com.example.coroutines.infra.adapter.ou.rest.client.news.newsdataio.NewsDataIOResponse
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = ["external.news.mode"], havingValue = "fallback", matchIfMissing = false)
class NewsFallBack : NewsClient {

    override fun fetch(city: String) = NewsDataIOResponse.empty()

}