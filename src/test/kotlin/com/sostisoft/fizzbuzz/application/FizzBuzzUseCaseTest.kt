package com.sostisoft.fizzbuzz.application

import com.sostisoft.fizzbuzz.domain.FizzBuzz
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FizzBuzzUseCaseTest {

    private val fizzBuzzUseCase = FizzBuzzUseCase()

    @Test
    fun `should return correct FizzBuzz sequence`() {
        val request = FizzBuzz(
            multipleOfFirst = 3,
            multipleOfSecond = 5,
            limit = 15,
            replacementForFirst = "Fizz",
            replacementForSecond = "Buzz"
        )

        val result = fizzBuzzUseCase.execute(request)

        assertThat(result).containsExactly(
            "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
            "11", "Fizz", "13", "14", "FizzBuzz"
        )
    }

    @Test
    fun `should return numbers when no multiples found`() {
        val request = FizzBuzz(
            multipleOfFirst = 7,
            multipleOfSecond = 11,
            limit = 10,
            replacementForFirst = "Foo",
            replacementForSecond = "Bar"
        )

        val result = fizzBuzzUseCase.execute(request)

        assertThat(result).containsExactly("1", "2", "3", "4", "5", "6", "Foo", "8", "9", "10")
    }

    @Test
    fun `should replace multiples correctly when first and second numbers are the same`() {
        val request = FizzBuzz(
            multipleOfFirst = 2,
            multipleOfSecond = 2,
            limit = 6,
            replacementForFirst = "Even",
            replacementForSecond = "Even"
        )

        val result = fizzBuzzUseCase.execute(request)

        assertThat(result).containsExactly("1", "EvenEven", "3", "EvenEven", "5", "EvenEven")
    }

    @Test
    fun `should return empty list when limit is zero`() {
        val request = FizzBuzz(
            multipleOfFirst = 3,
            multipleOfSecond = 5,
            limit = 0,
            replacementForFirst = "Fizz",
            replacementForSecond = "Buzz"
        )

        val result = fizzBuzzUseCase.execute(request)

        assertThat(result).isEmpty()
    }
}
