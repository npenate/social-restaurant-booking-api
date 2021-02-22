package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.entities.Restaurant
import com.nelo.socialrestaurant.testherlpers.valuegenerators.PointValueGenerator
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import org.springframework.data.geo.Point
import java.time.LocalDateTime
import java.util.UUID

class RestaurantBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var name: String = StringValueGenerator.generateAlphabetString()
    private set
  var location = PointValueGenerator.generateLocationPoint()
    private set
  var scheduledAt: LocalDateTime = LocalDateTime.now()
    private set
  var zipCode = StringValueGenerator.generateZipCodeString()
    private set

  fun build() =
    Restaurant(
      id = id,
      name = name,
      location = location,
      zipCode = zipCode,
      scheduledAt = scheduledAt
    )

  fun id(id: UUID) = apply { this.id = id }
  fun name(name: String) = apply { this.name = name }
  fun location(location: Point) = apply { this.location = location }
  fun scheduledAt(scheduledAt: LocalDateTime) = apply { this.scheduledAt = scheduledAt }
  fun zipCode(zipCode: String) = apply { this.zipCode = zipCode }
}
