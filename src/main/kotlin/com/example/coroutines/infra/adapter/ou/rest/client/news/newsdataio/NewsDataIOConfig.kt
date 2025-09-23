package com.example.coroutines.infra.adapter.ou.rest.client.news.newsdataio

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value

class NewsDataIOConfig(
    @param:Value("\${external.news.newsdataio.token}") private val applicationToken: String
) : RequestInterceptor {

    override fun apply(req: RequestTemplate?) {
        req?.query("apikey", applicationToken)
    }

}