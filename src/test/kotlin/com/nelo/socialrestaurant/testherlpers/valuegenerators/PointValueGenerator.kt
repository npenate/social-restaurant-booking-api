package com.nelo.socialrestaurant.testherlpers.valuegenerators

import org.springframework.data.geo.Point
import kotlin.random.Random

class PointValueGenerator {
  companion object {
    fun generateLocationPoint() = Point(Random.nextDouble() * 10, Random.nextDouble() * 10)
  }
}