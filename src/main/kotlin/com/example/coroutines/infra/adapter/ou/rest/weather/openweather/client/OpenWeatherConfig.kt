package com.example.coroutines.infra.adapter.ou.rest.weather.openweather.client

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value

class OpenWeatherConfig(
    @param:Value("\${external.weather.openweather.token}") private val applicationToken: String
) : RequestInterceptor {

    override fun apply(req: RequestTemplate?) {
        req?.query("appid", applicationToken)
    }

}