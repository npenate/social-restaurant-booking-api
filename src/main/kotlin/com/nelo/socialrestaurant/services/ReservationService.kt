package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.entities.Table
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import com.nelo.socialrestaurant.services.availabilitystrategy.AvailabilityService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService(
  val reservationsRepository: ReservationsRepository,
  val availabilityService: AvailabilityService
) {

  fun existsOverlappingReservations(
    diners: Collection<Diner>,
    start: LocalDateTime,
    end: LocalDateTime
  ): Boolean {
    val reservations = reservationsRepository.findByDinersInAndScheduledAtBetween(diners, start, end)
    return !reservations.isEmpty()
  }

  fun getAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Pageable
  ): Collection<Table> {
    return availabilityService.findAvailableTables(diners, scheduleAt, page)
  }
}
