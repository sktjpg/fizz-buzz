package com.sostisoft.fizzbuzz.domain.ports

import com.sostisoft.fizzbuzz.domain.FizzBuzz
import com.sostisoft.fizzbuzz.domain.Stats

interface StatsRepository {

    fun save(request: FizzBuzz)

    fun getMostFrequentRequest(): Stats?

}