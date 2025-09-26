package com.example.coroutines.util

data class DataAggregationEntry(val mode:String, val avg: Long, val attempts: MutableList<Long>? = mutableListOf()) {

    fun message() = "\nMode: $mode = average: $avg. All attempts: $attempts"

}

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
            DataAggregationEntry(k, avg, v)
        }.values.sortedBy { it.avg }.map { it.message() }

        fun print() {
            println(aggregateMessage())
        }

    }

}