package com.nelo.socialrestaurant.repositories

import com.nelo.socialrestaurant.entities.FoodClassification
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FoodClassificationsRepository : CrudRepository<FoodClassification, UUID>
