package com.example.coroutines.infra.adapter.ou.rest.client.news

import com.example.coroutines.infra.adapter.ou.rest.client.news.newsdataio.NewsDataIOResponse

interface NewsClient {

    fun fetch(city: String): NewsDataIOResponse

}