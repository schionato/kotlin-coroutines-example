package com.example.coroutines.infra.adapter.ou.rest.news.newsdataio

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "news-data-io",
    url = "\${external.news.newsdataio.url}",
    configuration = [NewsDataIOConfig::class]
)
@WhenNewsDataIsActive
interface NewsDataIOAPI {

    @GetMapping("?country=br&q={city}&size=10")
    fun fetch(@PathVariable("city") city: String): NewsDataIOResponse

}