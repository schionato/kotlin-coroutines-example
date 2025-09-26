package com.example.coroutines.infra.adapter.ou.rest.news.newsdataio

data class NewsDataIOResponse(val results: List<NewsDataIOArticle>) {

    companion object {
        fun empty() = NewsDataIOResponse(listOf())
    }

}

data class NewsDataIOArticle(val title: String, val link: String, val description: String)
