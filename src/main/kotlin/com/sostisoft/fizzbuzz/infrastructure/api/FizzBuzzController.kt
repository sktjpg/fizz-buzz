package com.sostisoft.fizzbuzz.infrastructure.api

import com.sostisoft.fizzbuzz.application.FizzBuzzUseCase
import com.sostisoft.fizzbuzz.application.StatsUseCase
import com.sostisoft.fizzbuzz.domain.FizzBuzz
import org.springframework.http.ResponseEntity
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
    ): ResponseEntity<List<String>> {
        val result = FizzBuzz(int1, int2, limit, str1, str2)
            .let(fizzBuzzUseCase::execute)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/stats")
    fun getStats(): ResponseEntity<Any> {
        val stats = statsUseCase.getMostFrequentRequest()
        return if (stats != null) {
            ResponseEntity.ok(stats)
        } else {
            ResponseEntity.status(404).body(mapOf("message" to "No statistics available yet"))
        }
    }
}
