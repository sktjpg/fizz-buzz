package com.sostisoft.fizzbuzz.infrastructure.api

import com.sostisoft.fizzbuzz.application.FizzBuzzUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FizzBuzzControllerUnitTest {

    private val fizzBuzzUseCase = FizzBuzzUseCase()
    private val controller = FizzBuzzController(fizzBuzzUseCase)

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

        val result = controller.getFizzBuzz(int1, int2, limit, str1, str2)

        assertEquals(expectedResponse, result)
    }
}
