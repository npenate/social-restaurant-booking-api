package com.nelo.socialrestaurant.testherlpers.valuegenerators

import kotlin.random.Random

class TableCapacityValueGenerator {
  companion object {
    fun generate() = Random.nextInt(2, 10).toByte()
  }
}