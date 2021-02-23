package com.nelo.socialrestaurant.models.entities

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity(name = "diners")
data class Diner(
  @Id
  var id: UUID,
  var name: String? = null,
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "diners_food_classifications",
    joinColumns = [JoinColumn(name = "diner_id")],
    inverseJoinColumns = [JoinColumn(name = "food_classification_id")]
  )
  var restrictions: Set<FoodClassification>,
  var lat: Float,
  var lon: Float,
  var zipCode: String,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)
