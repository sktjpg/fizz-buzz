package com.sostisoft.fizzbuzz.infrastructure.api

import com.sostisoft.fizzbuzz.application.FizzBuzzUseCase
import com.sostisoft.fizzbuzz.application.StatsUseCase
import com.sostisoft.fizzbuzz.domain.FizzBuzz
import com.sostisoft.fizzbuzz.domain.Stats
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FizzBuzzController(
    private val fizzBuzzUseCase: FizzBuzzUseCase,
    private val statsUseCase: StatsUseCase,
) {

    @GetMapping("/fizzbuzz")
    fun getFizzBuzz(
        @RequestParam int1: Int,
        @RequestParam int2: Int,
        @RequestParam limit: Int,
        @RequestParam str1: String,
        @RequestParam str2: String
    ): List<String> {
        return FizzBuzz(int1, int2, limit, str1, str2)
            .let(fizzBuzzUseCase::execute)
    }

    @GetMapping("/stats")
    fun getStats(): Stats? {
        return statsUseCase.getMostFrequentRequest()
    }
}
