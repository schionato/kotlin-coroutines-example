package com.example.coroutines.util

class DurationAggregator() {

    companion object {
        private val DURATIONS: MutableMap<String, MutableList<Long>?> = mutableMapOf()

        fun add(k: String, duration: Long) {
            DURATIONS.getOrPut(k) { mutableListOf() }?.add(duration)
        }

        fun aggregateMessage() = DURATIONS.mapValues { (k, v) ->
            val sum = v?.sum() ?: 0
            val times = v?.size ?: 0
            val avg = sum / times
            "\nMode: $k = average: $avg. All attempts: $v"
        }.values

        fun print() {
            println(aggregateMessage())
        }

    }

}