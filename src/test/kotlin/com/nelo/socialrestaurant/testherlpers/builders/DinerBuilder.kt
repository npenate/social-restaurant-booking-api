package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.models.entities.FoodClassification
import com.nelo.socialrestaurant.testherlpers.valuegenerators.LatLonValueGenerator
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import java.util.UUID

class DinerBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var name: String = StringValueGenerator.generateAlphabetString()
    private set
  var restrictions = setOf<FoodClassification>()
    private set
  var lat = LatLonValueGenerator.generateLatLon()
    private set
  var lon = LatLonValueGenerator.generateLatLon()
    private set
  var zipCode = StringValueGenerator.generateZipCodeString()
    private set

  fun build() =
    Diner(
      id,
      name,
      restrictions,
      lat,
      lon,
      zipCode
    )

  fun id(id: UUID) = apply { this.id = id }
  fun name(name: String) = apply { this.name = name }
  fun restrictions(restrictions: Set<FoodClassification>) = apply { this.restrictions = restrictions }
  fun lat(lat: Float) = apply { this.lat = lat }
  fun lon(lon: Float) = apply { this.lon = lon }
  fun zipCode(zipCode: String) = apply { this.zipCode = zipCode }
}