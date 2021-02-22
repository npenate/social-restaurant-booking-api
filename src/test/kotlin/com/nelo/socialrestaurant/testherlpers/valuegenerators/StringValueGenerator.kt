package com.nelo.socialrestaurant.testherlpers.valuegenerators

class StringValueGenerator {
  companion object {
    private val alphabetCharPool : List<Char> = ('a'..'z') + ('A'..'Z')
    fun generateAlphabetString(size: Int = 5) = (1..size)
      .map { kotlin.random.Random.nextInt(0, alphabetCharPool.size) }
      .map(alphabetCharPool::get)
      .joinToString("");
  }
}