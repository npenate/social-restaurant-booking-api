package com.nelo.socialrestaurant.controllers.dto

import com.nelo.socialrestaurant.models.entities.Reservation
import java.time.LocalDateTime
import java.util.UUID

class CreatedReservationResponse(
  var id: UUID,
  var scheduledAt: LocalDateTime,
  var createdAt: LocalDateTime
) {

  companion object {
    fun fromEntity(reservation: Reservation) = CreatedReservationResponse(
      id = reservation.id,
      scheduledAt = reservation.scheduledAt,
      createdAt = reservation.createdAt
    )
  }
}
