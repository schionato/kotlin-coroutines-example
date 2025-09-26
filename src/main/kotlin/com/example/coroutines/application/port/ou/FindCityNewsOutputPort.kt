package com.example.coroutines.application.port.ou

import com.example.coroutines.domain.Article

interface FindCityNewsOutputPort {

    fun filteringBy(query: String): List<Article>

}