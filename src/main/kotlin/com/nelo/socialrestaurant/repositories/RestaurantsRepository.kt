package com.nelo.socialrestaurant.repositories

import com.nelo.socialrestaurant.models.entities.Restaurant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RestaurantsRepository : CrudRepository<Restaurant, UUID>
