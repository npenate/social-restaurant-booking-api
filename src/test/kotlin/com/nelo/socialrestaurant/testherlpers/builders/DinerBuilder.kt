package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.entities.FoodClassification
import com.nelo.socialrestaurant.testherlpers.valuegenerators.PointValueGenerator
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import org.springframework.data.geo.Point
import java.util.UUID

class DinerBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var name: String = StringValueGenerator.generateAlphabetString()
    private set
  var restrictions = setOf<FoodClassification>()
    private set
  var location = PointValueGenerator.generateLocationPoint()
    private set
  var zipCode = StringValueGenerator.generateZipCodeString()
    private set

  fun build() =
    Diner(
      id,
      name,
      restrictions,
      location,
      zipCode
    )

  fun id(id: UUID) = apply { this.id = id }
  fun name(name: String) = apply { this.name = name }
  fun restrictions(restrictions: Set<FoodClassification>) = apply { this.restrictions = restrictions }
  fun location(location: Point) = apply { this.location = location }
  fun zipCode(zipCode: String) = apply { this.zipCode = zipCode }
}