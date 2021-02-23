package com.nelo.socialrestaurant.controllers.dto

import com.nelo.socialrestaurant.models.entities.Restaurant
import java.util.UUID

data class AvailableRestaurantResponse(
  var id: UUID,
  var name: String,
  var zipCode: String,
  var lat: Float,
  var lon: Float,
  var tables: MutableList<TableResponse> = mutableListOf()
) {
  fun addTable(table: TableResponse) {
    tables.add(table)
  }

  companion object {
    fun fromEntity(restaurant: Restaurant) = AvailableRestaurantResponse(
      id = restaurant.id,
      name = restaurant.name,
      zipCode = restaurant.zipCode,
      lat = restaurant.lat,
      lon = restaurant.lon
    )
  }
}
