package com.sostisoft.fizzbuzz.application

import com.sostisoft.fizzbuzz.domain.Stats
import com.sostisoft.fizzbuzz.infrastructure.repository.FizzBuzzStatsRepository
import org.springframework.stereotype.Service

@Service
class StatsUseCase(private val statsRepository: FizzBuzzStatsRepository) {

    fun getMostFrequentRequest(): Stats? {
        return statsRepository.getMostFrequentRequest()
    }
}
