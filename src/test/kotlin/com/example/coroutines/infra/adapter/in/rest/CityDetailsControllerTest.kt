package com.example.coroutines.infra.adapter.`in`.rest

import com.example.coroutines.util.Chronometer
import com.example.coroutines.util.DurationAggregator
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import kotlin.test.assertTrue

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityDetailsControllerTest {

    var restTemplate: RestTemplate = RestTemplate()

    @LocalServerPort
    var port: Int? = 0

    @Nested
    @DisplayName("GET cities?q={city-name}&method={method}")
    @Execution(ExecutionMode.SAME_THREAD)
    inner class GetCityDetails {

        var chronometer = Chronometer()

        @ParameterizedTest
        @EnumSource(FetchType::class)
        fun findUsingParallel(type: FetchType) {

            for (i in 0 until 15) {
                chronometer.start()

                doRequest(type.name, "Campinas")

                chronometer.stop()
                DurationAggregator.add(type.name, chronometer.duration())
            }

        }

        fun doRequest(mode: String, city: String) {
            val response = restTemplate.getForEntity<Void>("http://localhost:$port/cities?q=$city&method=$mode")
            assertTrue(response.statusCode.is2xxSuccessful)
        }
    }

    companion object {

        @AfterAll
        @JvmStatic
        fun tearDown() {
            DurationAggregator.print()
        }

    }

}