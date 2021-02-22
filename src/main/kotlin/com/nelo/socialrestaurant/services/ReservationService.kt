package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService(
  val reservationsRepository: ReservationsRepository
) {

  fun existsOverlappingReservations(diners: Collection<Diner>, start: LocalDateTime, end: LocalDateTime): Boolean {
    val reservations = reservationsRepository.findByDinersInAndScheduledAtBetween(diners, start, end)
    return !reservations.isEmpty()
  }
}
