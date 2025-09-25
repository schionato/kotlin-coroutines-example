package com.example.coroutines.infra.adapter.ou.rest.client.news.newsdataio

import com.example.coroutines.infra.adapter.ou.rest.client.news.NewsClient
import feign.Headers
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@ConditionalOnProperty(
    name = ["external.news.mode"],
    havingValue = "newsdataio",
    matchIfMissing = false
)
@FeignClient(
    name = "news-data-io",
    url = "\${external.news.newsdataio.url}",
    configuration = [NewsDataIOConfig::class]
)
interface NewsDataIO : NewsClient {

    @Headers(
        "Cache-Control: no-cache, no-store, must-revalidate",
        "Pragma: no-cache",
        "Expires: 0"
    )
    @GetMapping("?country=br&q={city}&size=10")
    override fun fetch(@PathVariable("city") city: String): NewsDataIOResponse

}