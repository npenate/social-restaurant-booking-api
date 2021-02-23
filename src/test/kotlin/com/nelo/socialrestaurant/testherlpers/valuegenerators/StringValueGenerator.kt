package com.nelo.socialrestaurant.testherlpers.valuegenerators

import kotlin.random.Random

class StringValueGenerator {
  companion object {
    private val alphabetCharPool : List<Char> = ('a'..'z') + ('A'..'Z')
    private val digitsCharPool : List<Char> = ('0'..'9').toList()
    private val foodClassificationPool = listOf("PALEO", "VEGAN", "VEGETARIAN", "DUKAN", "ATKINS", "HCG")

    fun generateAlphabetString(size: Int = 5) = (1..size)
      .map { Random.nextInt(0, alphabetCharPool.size) }
      .map(alphabetCharPool::get)
      .joinToString("");

    fun generateZipCodeString(size: Int = 5) = (1..size)
      .map { Random.nextInt(0, digitsCharPool.size) }
      .map(digitsCharPool::get)
      .joinToString("");

    fun generateFoodClassificationString(size: Int = 5) =
      foodClassificationPool[Random.nextInt(0, foodClassificationPool.size)]
  }
}