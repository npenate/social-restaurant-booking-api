package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.entities.FoodClassification
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import java.util.UUID

class DinerBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var name: String = StringValueGenerator.generateAlphabetString()
    private set
  var restrictions = setOf<FoodClassification>()
    private set

  fun build() =
    Diner(
      id,
      name,
      restrictions
    )

  fun id(id: UUID) = apply { this.id = id }
  fun name(name: String) = apply { this.name = name }
  fun restrictions(restrictions: Set<FoodClassification>) = apply { this.restrictions = restrictions }
}