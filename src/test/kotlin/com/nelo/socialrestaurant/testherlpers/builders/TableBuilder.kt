package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.entities.FoodClassification
import com.nelo.socialrestaurant.entities.Restaurant
import com.nelo.socialrestaurant.entities.Table
import com.nelo.socialrestaurant.testherlpers.valuegenerators.PointValueGenerator
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import com.nelo.socialrestaurant.testherlpers.valuegenerators.TableCapacityValueGenerator
import org.springframework.data.geo.Point
import java.util.UUID

class TableBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var capacity: Byte = TableCapacityValueGenerator.generate()
    private set
  var restaurant = RestaurantBuilder().build()
    private set
  var location = PointValueGenerator.generateLocationPoint()
    private set
  var zipCode = StringValueGenerator.generateZipCodeString()
    private set

  fun build() =
    Table(
      id = id,
      capacity = capacity,
      restaurant = restaurant,
      location = location,
      zipCode = zipCode
    )

  fun id(id: UUID) = apply { this.id = id }
  fun capacity(capacity: Byte) = apply { this.capacity = capacity }
  fun restaurant(restaurant: Restaurant) = apply { this.restaurant = restaurant }
  fun location(location: Point) = apply { this.location = location }
  fun zipCode(zipCode: String) = apply { this.zipCode = zipCode }
}
