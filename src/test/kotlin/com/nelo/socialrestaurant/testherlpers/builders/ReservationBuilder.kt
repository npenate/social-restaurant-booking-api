package com.nelo.socialrestaurant.testherlpers.builders

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.entities.Reservation
import com.nelo.socialrestaurant.entities.Table
import java.time.LocalDateTime
import java.util.UUID

class ReservationBuilder() {

  var id: UUID = UUID.randomUUID()
    private set
  var diners = emptySet<Diner>()
    private set
  var table = Table(UUID.randomUUID(), 2)
    private set
  var scheduledAt = LocalDateTime.now()
    private set
  fun build() =
    Reservation(
      id,
      diners,
      table,
      scheduledAt
    )

  fun id(id: UUID) = apply { this.id = id }
  fun diners(diners: Set<Diner>) = apply { this.diners = diners }
  fun table(table: Table) = apply { this.table = table }
  fun scheduledAt(scheduledAt: LocalDateTime) = apply { this.scheduledAt = scheduledAt }
}