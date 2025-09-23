package com.example.coroutines.domain

data class CityDetails(
    val name: String,
    val coordinates: Coordinates,
    val weather: Weather,
    val news: List<Article>
) {

    companion object {

        fun of(city: String, weather: Weather, coordinates: Coordinates, news: List<Article>) = CityDetails(
            name = city,
            weather = weather,
            coordinates = coordinates,
            news = news
        )

    }

}



