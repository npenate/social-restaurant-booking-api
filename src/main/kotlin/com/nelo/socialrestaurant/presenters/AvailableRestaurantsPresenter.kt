package com.nelo.socialrestaurant.presenters

import com.nelo.socialrestaurant.controllers.dto.AvailableRestaurantResponse
import com.nelo.socialrestaurant.controllers.dto.TableResponse
import com.nelo.socialrestaurant.entities.Table
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.collections.ArrayList

@Service
class AvailableRestaurantsPresenter {
  private fun present(tables: ArrayList<Table>): Collection<AvailableRestaurantResponse> {
    val restaurantsMap = emptyMap<UUID, AvailableRestaurantResponse>()
    tables.forEach {
      if (!restaurantsMap.contains(it.restaurant.id)) {
        val restaurant = AvailableRestaurantResponse.fromEntity(it.restaurant)
        restaurantsMap.plus(Pair(restaurant.id, restaurant))
      }
      restaurantsMap[it.restaurant.id]?.addTable(TableResponse.fromEntity(it))
    }

    return restaurantsMap.values
  }
}
