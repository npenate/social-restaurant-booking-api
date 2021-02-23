package com.nelo.socialrestaurant.controllers.dto

import com.nelo.socialrestaurant.entities.Restaurant
import org.springframework.data.geo.Point
import java.util.UUID

data class AvailableRestaurantResponse(
  var id: UUID,
  var name: String,
  var location: Point,
  var zipCode: String,
  var tables: List<TableResponse> = emptyList()
) {
  fun addTable(table: TableResponse) {
    tables.plus(table)
  }

  companion object {
    fun fromEntity(restaurant: Restaurant) = AvailableRestaurantResponse(
      id = restaurant.id,
      name = restaurant.name,
      location = restaurant.location,
      zipCode = restaurant.zipCode
    )
  }
}
