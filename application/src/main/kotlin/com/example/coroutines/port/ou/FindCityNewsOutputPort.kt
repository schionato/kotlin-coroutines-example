package com.example.coroutines.port.ou

import com.example.coroutines.domain.Article

fun interface FindCityNewsOutputPort {

    fun filteringBy(query: String): List<Article>

}