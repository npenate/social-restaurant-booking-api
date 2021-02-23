package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationsService(
  val reservationsRepository: ReservationsRepository
) {

  fun existsOverlappingReservations(
    diners: Collection<Diner>,
    start: LocalDateTime,
    end: LocalDateTime
  ): Boolean {
    val reservations = reservationsRepository.findByDinersInAndScheduledAtBetween(diners, start, end)
    return !reservations.isEmpty()
  }
}
