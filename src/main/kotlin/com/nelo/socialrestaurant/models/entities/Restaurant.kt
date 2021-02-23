package com.nelo.socialrestaurant.models.entities

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity(name = "restaurants")
data class Restaurant(
  @Id
  var id: UUID,
  var name: String,
  var lat: Float,
  var lon: Float,
  var zipCode: String,
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "restaurants_food_classifications",
    joinColumns = [JoinColumn(name = "restaurant_id")],
    inverseJoinColumns = [JoinColumn(name = "food_classification_id")])
  var endorsements: Set<FoodClassification>,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)
