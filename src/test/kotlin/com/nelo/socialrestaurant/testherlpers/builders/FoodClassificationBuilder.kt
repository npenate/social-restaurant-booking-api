package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.models.entities.FoodClassification
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import java.util.UUID

class FoodClassificationBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var name: String = StringValueGenerator.generateFoodClassificationString()
    private set
  var description = ""
    private set

  fun build() =
    FoodClassification(
      id,
      name,
      description
    )

  fun id(id: UUID) = apply { this.id = id }
  fun name(name: String) = apply { this.name = name }
  fun description(description: String) = apply { this.description = description }
}