package com.sostisoft.fizzbuzz.infrastructure.repository

import com.sostisoft.fizzbuzz.domain.FizzBuzz
import com.sostisoft.fizzbuzz.domain.Stats
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FizzBuzzStatsRepositoryTest {

    private lateinit var repository: FizzBuzzStatsRepository

    @BeforeEach
    fun setUp() {
        repository = FizzBuzzStatsRepository()
    }

    @Test
    fun `should save requests and retrieve most frequent request`() {
        val request1 = FizzBuzz(3, 5, 15, "Fizz", "Buzz")
        val request2 = FizzBuzz(2, 4, 10, "Ping", "Pong")

        repeat(5) { repository.save(request1) }
        repeat(3) { repository.save(request2) }

        val result = repository.getMostFrequentRequest()

        assertEquals(Stats(request1, 5), result)
    }

    @Test
    fun `should return null when no requests are present`() {
        val result = repository.getMostFrequentRequest()
        assertNull(result)
    }
}
