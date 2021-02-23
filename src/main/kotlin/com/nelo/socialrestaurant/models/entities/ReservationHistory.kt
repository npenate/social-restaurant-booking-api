package com.nelo.socialrestaurant.models.entities

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "reservations_history")
data class ReservationHistory(
  @Id
  var id: UUID,
  var tableId: UUID,
  var scheduledAt: LocalDateTime,
  var deleted: Boolean? = false,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
) {
  companion object {
    fun fromReservation(reservation: Reservation) = ReservationHistory(
      id = reservation.id,
      tableId = reservation.table.id,
      scheduledAt = reservation.scheduledAt,
      createdAt = reservation.createdAt,
      updatedAt = reservation.updatedAt
    )
  }
}
