package com.sostisoft.fizzbuzz.application

import com.sostisoft.fizzbuzz.domain.FizzBuzz
import com.sostisoft.fizzbuzz.domain.Stats
import com.sostisoft.fizzbuzz.infrastructure.repository.FizzBuzzStatsRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals
import kotlin.test.assertNull

class StatsUseCaseTest {

    private val statsRepository: FizzBuzzStatsRepository = Mockito.mock(FizzBuzzStatsRepository::class.java)
    private lateinit var statsUseCase: StatsUseCase

    @BeforeEach
    fun setUp() {
        statsUseCase = StatsUseCase(statsRepository)
    }

    @Test
    fun `should return most frequent request when available`() {
        val expectedStats = Stats(FizzBuzz(3, 5, 15, "Fizz", "Buzz"), 10)

        `when`(statsRepository.getMostFrequentRequest()).thenReturn(expectedStats)

        val result = statsUseCase.getMostFrequentRequest()

        assertEquals(expectedStats, result)
        Mockito.verify(statsRepository, Mockito.times(1)).getMostFrequentRequest()
    }

    @Test
    fun `should return null when no stats are available`() {
        `when`(statsRepository.getMostFrequentRequest()).thenReturn(null)

        val result = statsUseCase.getMostFrequentRequest()

        assertNull(result)
        Mockito.verify(statsRepository, Mockito.times(1)).getMostFrequentRequest()
    }
}
