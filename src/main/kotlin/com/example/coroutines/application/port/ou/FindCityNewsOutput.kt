package com.example.coroutines.application.port.ou

import com.example.coroutines.domain.Article

interface FindCityNewsOutput {

    fun filteringBy(query: String): List<Article>

}