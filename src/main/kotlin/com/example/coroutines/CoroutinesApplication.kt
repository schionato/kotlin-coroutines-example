package com.example.coroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class CoroutinesApplication

fun main(args: Array<String>) {
    runApplication<CoroutinesApplication>(*args)
}
