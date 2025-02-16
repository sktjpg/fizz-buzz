package com.sostisoft.fizzbuzz.application

import com.sostisoft.fizzbuzz.domain.FizzBuzz
import com.sostisoft.fizzbuzz.domain.ports.StatsRepository
import org.springframework.stereotype.Component

@Component
class FizzBuzzUseCase(private val statsRepository: StatsRepository) {

    fun execute(fizzBuzz: FizzBuzz): List<String> {
        return (1..fizzBuzz.limit)
            .map { num -> process(num, fizzBuzz) }
            .also { statsRepository.save(fizzBuzz) }
    }

    private fun process(num: Int, fizzBuzz: FizzBuzz): String =
        when {
            num % fizzBuzz.multipleOfFirst == 0 && num % fizzBuzz.multipleOfSecond == 0 -> fizzBuzz.replacementForFirst + fizzBuzz.replacementForSecond
            num % fizzBuzz.multipleOfFirst == 0 -> fizzBuzz.replacementForFirst
            num % fizzBuzz.multipleOfSecond == 0 -> fizzBuzz.replacementForSecond
            else -> num.toString()
        }

}