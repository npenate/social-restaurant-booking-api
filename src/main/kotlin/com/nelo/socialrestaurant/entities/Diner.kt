package com.nelo.socialrestaurant.entities

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity(name = "diners")
data class Diner(
  @Id
  @GeneratedValue(generator = "UUID")
  var id: UUID,
  var name: String? = null,
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "diners_food_classifications",
    joinColumns=[JoinColumn(name = "food_classification_id")],
    inverseJoinColumns=[JoinColumn(name = "diner_id")]
  )
  var restrictions: Set<FoodClassification>,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)
