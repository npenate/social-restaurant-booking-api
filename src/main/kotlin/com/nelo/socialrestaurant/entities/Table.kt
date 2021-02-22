package com.nelo.socialrestaurant.entities

import org.springframework.data.geo.Point
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tables")
data class Table(
  @Id
  @GeneratedValue(generator = "UUID")
  var id: UUID,
  var capacity: Byte,
  var description: String? = null,
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn( name = "restaurant_id")
  var restaurant: Restaurant,
  var location: Point,
  var zipCode: String,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)
