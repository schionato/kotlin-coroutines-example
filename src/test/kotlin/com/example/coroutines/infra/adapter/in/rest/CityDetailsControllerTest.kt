package com.example.coroutines.infra.adapter.`in`.rest

import com.example.coroutines.util.Chronometer
import com.example.coroutines.util.DurationAggregator
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityDetailsControllerTest {

    var restTemplate: RestTemplate = RestTemplate()

    @LocalServerPort
    var port: Int? = 0

    @Nested
    @DisplayName("GET cities?q={city-name}&method={method}")
    @Execution(ExecutionMode.SAME_THREAD)
    inner class GetCityDetails {

        var mode: String? = null

        var chronometer = Chronometer()

        @BeforeEach
        fun setUp() {
            chronometer.start()
        }

        @ParameterizedTest
        @CsvSource(
            delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            mode        | city
            SYNC        | campinas
            ASYNC       | brasilia
            PARALLEL    | manaus
            SYNC        | ubatuba
            ASYNC       | florianopolis
            PARALLEL    | curitiba
            SYNC        | brasilia
            SYNC        | manaus
            SYNC        | florianopolis
            SYNC        | curitiba
            ASYNC       | campinas
            ASYNC       | manaus
            ASYNC       | ubatuba
            PARALLEL    | campinas
            PARALLEL    | brasilia
            PARALLEL    | ubatuba
            PARALLEL    | florianopolis"""
        )
        fun findUsingParallel(selectedMode: String, city: String) {
            mode = selectedMode
            doRequest(city)
        }

        fun doRequest(city: String) {
            val response = restTemplate.getForEntity<Void>("http://localhost:$port/cities?q=$city&method=$mode")
            assertTrue(response.statusCode.is2xxSuccessful)
        }

        @AfterEach
        fun tearDown() {
            chronometer.stop()

            DurationAggregator.add(mode ?: "", chronometer.duration())
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