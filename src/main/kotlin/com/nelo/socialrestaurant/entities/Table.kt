package com.nelo.socialrestaurant.entities

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "tables")
data class Table(
  @Id
  @GeneratedValue(generator = "UUID")
  var id: UUID,
  var capacity: Byte,
  var description: String? = null,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)
