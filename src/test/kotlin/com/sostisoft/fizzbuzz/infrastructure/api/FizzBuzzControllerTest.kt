package com.sostisoft.fizzbuzz.infrastructure.api

import com.sostisoft.fizzbuzz.application.FizzBuzzUseCase
import com.sostisoft.fizzbuzz.application.StatsUseCase
import com.sostisoft.fizzbuzz.domain.FizzBuzz
import com.sostisoft.fizzbuzz.domain.Stats
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

class FizzBuzzControllerUnitTest {

    private val fizzBuzzUseCase: FizzBuzzUseCase = Mockito.mock(FizzBuzzUseCase::class.java)
    private val statsUseCase: StatsUseCase = Mockito.mock(StatsUseCase::class.java)

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
            "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"
        )

        `when`(fizzBuzzUseCase.execute(FizzBuzz(int1, int2, limit, str1, str2))).thenReturn(expectedResponse)

        val result = controller.getFizzBuzz(int1, int2, limit, str1, str2)

        assertEquals(expectedResponse, result)
        Mockito.verify(fizzBuzzUseCase, Mockito.times(1)).execute(FizzBuzz(int1, int2, limit, str1, str2))
    }

    @Test
    fun `should return most frequent fizzbuzz request stats`() {
        val statsResponse = Stats(FizzBuzz(3, 5, 15, "Fizz", "Buzz"), 10)

        `when`(statsUseCase.getMostFrequentRequest()).thenReturn(statsResponse)

        val result = controller.getStats()

        assertEquals(statsResponse, result)
        Mockito.verify(statsUseCase, Mockito.times(1)).getMostFrequentRequest()
    }
}
