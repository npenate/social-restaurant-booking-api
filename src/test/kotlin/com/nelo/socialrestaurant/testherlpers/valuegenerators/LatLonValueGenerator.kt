package com.nelo.socialrestaurant.testherlpers.valuegenerators

import kotlin.random.Random

class LatLonValueGenerator {
  companion object {
    fun generateLatLon() = (Random.nextDouble() * 10).toFloat()
  }
}