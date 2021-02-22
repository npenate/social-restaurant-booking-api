package com.nelo.socialrestaurant.entities

import org.springframework.data.geo.Point
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "restaurants")
data class Restaurant(
  @Id
  @GeneratedValue(generator = "UUID")
  var id: UUID,
  var name: String,
  var location: Point,
  var zipCode: String,
  var scheduledAt: LocalDateTime,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)
