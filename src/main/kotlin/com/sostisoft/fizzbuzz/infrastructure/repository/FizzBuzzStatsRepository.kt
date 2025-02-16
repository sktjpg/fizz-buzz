package com.sostisoft.fizzbuzz.infrastructure.repository

import com.sostisoft.fizzbuzz.domain.FizzBuzz
import com.sostisoft.fizzbuzz.domain.Stats
import com.sostisoft.fizzbuzz.domain.ports.StatsRepository
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

@Component
class FizzBuzzStatsRepository : StatsRepository {
    private val requestCounts = ConcurrentHashMap<FizzBuzz, AtomicInteger>()

    override fun save(request: FizzBuzz) {
        requestCounts.computeIfAbsent(request) { AtomicInteger(0) }.incrementAndGet()
    }

    override fun getMostFrequentRequest(): Stats? {
        return requestCounts
            .maxByOrNull { it.value.get() }
            ?.let { Stats(it.key, it.value.get()) }
    }
}
