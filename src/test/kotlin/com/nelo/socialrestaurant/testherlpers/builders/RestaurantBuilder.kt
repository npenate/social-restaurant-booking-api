package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.models.entities.FoodClassification
import com.nelo.socialrestaurant.models.entities.Restaurant
import com.nelo.socialrestaurant.testherlpers.valuegenerators.LatLonValueGenerator
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import java.util.UUID

class RestaurantBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var name: String = StringValueGenerator.generateAlphabetString()
    private set
  var lat = LatLonValueGenerator.generateLatLon()
    private set
  var lon = LatLonValueGenerator.generateLatLon()
    private set
  var zipCode = StringValueGenerator.generateZipCodeString()
    private set
  var endorsements = setOf(FoodClassificationBuilder().name("FOOD").build())
    private set

  fun build() =
    Restaurant(
      id = id,
      name = name,
      lat = lat,
      lon = lon,
      zipCode = zipCode,
      endorsements = endorsements
    )

  fun id(id: UUID) = apply { this.id = id }
  fun name(name: String) = apply { this.name = name }
  fun lat(lat: Float) = apply { this.lat = lat }
  fun lon(lon: Float) = apply { this.lon = lon }
  fun zipCode(zipCode: String) = apply { this.zipCode = zipCode }
  fun endorsements(endorsements: Set<FoodClassification>) = apply { this.endorsements = endorsements }
}
