package com.sostisoft.fizzbuzz.domain

data class FizzBuzz(
    val multipleOfFirst: Int,
    val multipleOfSecond: Int,
    val limit: Int,
    val replacementForFirst: String,
    val replacementForSecond: String
)