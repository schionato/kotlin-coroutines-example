package com.example.coroutines.util

import java.time.Instant

class Chronometer(private var start: Long = 0, private var stop: Long = 0) {

    fun start() {
        start = Instant.now().toEpochMilli()
    }

    fun stop() {
        stop = Instant.now().toEpochMilli()
    }

    fun duration() = stop - start
}