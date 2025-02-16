package com.sostisoft.fizzbuzz.application

import com.sostisoft.fizzbuzz.domain.FizzBuzz
import org.springframework.stereotype.Component

@Component
class FizzBuzzUseCase {

    fun execute(request: FizzBuzz): List<String> {
        return (1..request.limit)
            .map { num -> process(num, request) }
    }

    private fun process(num: Int, request: FizzBuzz): String =
        when {
            num % request.multipleOfFirst == 0 && num % request.multipleOfSecond == 0 -> request.replacementForFirst + request.replacementForSecond
            num % request.multipleOfFirst == 0 -> request.replacementForFirst
            num % request.multipleOfSecond == 0 -> request.replacementForSecond
            else -> num.toString()
        }

}