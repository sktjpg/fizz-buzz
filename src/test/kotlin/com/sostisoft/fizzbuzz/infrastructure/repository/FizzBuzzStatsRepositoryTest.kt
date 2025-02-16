package com.sostisoft.fizzbuzz.infrastructure.api

import com.sostisoft.fizzbuzz.application.FizzBuzzUseCase
import com.sostisoft.fizzbuzz.application.StatsUseCase
import com.sostisoft.fizzbuzz.domain.FizzBuzz
import com.sostisoft.fizzbuzz.domain.Stats
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

class FizzBuzzControllerUnitTest {

    private val fizzBuzzUseCase = Mockito.mock(FizzBuzzUseCase::class.java)
    private val statsUseCase = Mockito.mock(StatsUseCase::class.java)

    private lateinit var controller: FizzBuzzController

    @BeforeEach
    fun setUp() {
        controller = FizzBuzzController(fizzBuzzUseCase, statsUseCase)
    }

    @Test
    fun `should return correct fizzbuzz sequence`() {
        val int1 = 3
        val int2 = 5
        val limit = 15
        val str1 = "Fizz"
        val str2 = "Buzz"

        val expectedResponse = listOf(
            "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
            "11", "Fizz", "13", "14", "FizzBuzz"
        )

        `when`(fizzBuzzUseCase.execute(FizzBuzz(int1, int2, limit, str1, str2)))
            .thenReturn(expectedResponse)

        val response = controller.getFizzBuzz(int1, int2, limit, str1, str2)

        assertEquals(200, response.statusCodeValue)
        assertEquals(expectedResponse, response.body)

        verify(fizzBuzzUseCase, times(1)).execute(FizzBuzz(int1, int2, limit, str1, str2))
    }

    @Test
    fun `should return most frequent request stats`() {
        val stats = Stats(FizzBuzz(3, 5, 15, "Fizz", "Buzz"), 10)

        `when`(statsUseCase.getMostFrequentRequest()).thenReturn(stats)

        val response = controller.getStats()

        assertEquals(200, response.statusCodeValue)
        assertEquals(stats, response.body)

        verify(statsUseCase, times(1)).getMostFrequentRequest()
    }

    @Test
    fun `should return 404 when no stats available`() {
        `when`(statsUseCase.getMostFrequentRequest()).thenReturn(null)

        val response = controller.getStats()

        assertEquals(404, response.statusCodeValue)
        assertEquals(mapOf("message" to "No statistics available yet"), response.body)

        verify(statsUseCase, times(1)).getMostFrequentRequest()
    }
}
