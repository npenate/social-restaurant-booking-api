package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.models.entities.Restaurant
import com.nelo.socialrestaurant.models.entities.Table
import com.nelo.socialrestaurant.testherlpers.valuegenerators.LatLonValueGenerator
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import com.nelo.socialrestaurant.testherlpers.valuegenerators.TableCapacityValueGenerator
import java.util.UUID

class TableBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var capacity: Byte = TableCapacityValueGenerator.generate()
    private set
  var restaurant = RestaurantBuilder().build()
    private set
  var lat = LatLonValueGenerator.generateLatLon()
    private set
  var lon = LatLonValueGenerator.generateLatLon()
    private set
  var zipCode = StringValueGenerator.generateZipCodeString()
    private set

  fun build() =
    Table(
      id = id,
      capacity = capacity,
      restaurant = restaurant,
      lat = lat,
      lon = lon,
      zipCode = zipCode
    )

  fun id(id: UUID) = apply { this.id = id }
  fun capacity(capacity: Byte) = apply { this.capacity = capacity }
  fun restaurant(restaurant: Restaurant) = apply { this.restaurant = restaurant }
  fun lat(lat: Float) = apply { this.lat = lat }
  fun lon(lon: Float) = apply { this.lon = lon }
  fun zipCode(zipCode: String) = apply { this.zipCode = zipCode }
}
