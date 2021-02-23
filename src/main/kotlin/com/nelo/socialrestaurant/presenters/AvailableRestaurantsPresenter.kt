package com.nelo.socialrestaurant.presenters

import com.nelo.socialrestaurant.controllers.dto.AvailableRestaurantResponse
import com.nelo.socialrestaurant.controllers.dto.TableResponse
import com.nelo.socialrestaurant.models.entities.Table
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AvailableRestaurantsPresenter {
  fun present(tables: Collection<Table>): Collection<AvailableRestaurantResponse> {
    val restaurantsMap = mutableMapOf<UUID, AvailableRestaurantResponse>()
    tables.forEach {
      if (!restaurantsMap.contains(it.restaurant.id)) {
        val restaurant = AvailableRestaurantResponse.fromEntity(it.restaurant)
        restaurantsMap[restaurant.id] = restaurant
      }
      restaurantsMap[it.restaurant.id]?.addTable(TableResponse.fromEntity(it))
    }

    return sortByTableCapacities(restaurantsMap.values)
  }

  private fun sortByTableCapacities(
    restaurants: Collection<AvailableRestaurantResponse>
  ): List<AvailableRestaurantResponse> {
    return restaurants.sortedWith(Comparator<AvailableRestaurantResponse>{
      a, b ->
        var minA = Byte.MAX_VALUE
        for (t in a.tables) minA = minA.coerceAtMost(t.capacity)
        var minB = Byte.MAX_VALUE
        for (t in b.tables) minB = minB.coerceAtMost(t.capacity)
        when {
          minA > minB -> 1
          minA < minB -> -1
          else -> 0
        }
    })
  }
}
