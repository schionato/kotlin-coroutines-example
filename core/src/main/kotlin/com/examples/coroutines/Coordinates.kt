package com.example.coroutines.domain

data class Coordinates(val latitude: Double, val longitude: Double) {

    companion object {

        fun empty() = Coordinates(latitude = -1.0, longitude = -1.0)

    }

}
