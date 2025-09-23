package com.example.coroutines.infra.adapter.ou.rest.client.news.newsdataio

data class NewsDataIOResponse(val results: List<NewsDataIOArticle>)

data class NewsDataIOArticle(val title: String, val link: String, val description: String)
