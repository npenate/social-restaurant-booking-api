package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.models.entities.Reservation
import com.nelo.socialrestaurant.models.entities.ReservationHistory
import com.nelo.socialrestaurant.repositories.ReservationsHistoryRepository
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Profile("!test")
class ReservationsCleanerService(
  val reservationsRepository: ReservationsRepository,
  val reservationsHistoryRepository: ReservationsHistoryRepository
) {
  @Scheduled(cron = "0 0 1 * * ?")
  fun cleanReservations() {
    val reservations = reservationsRepository.findByScheduledAtLessThan(
      LocalDateTime.now().minusHours(Reservation.RESERVATION_DURATION_HOURS)
    )
    reservations.forEach {
      reservationsHistoryRepository.save(ReservationHistory.fromReservation(it))
      reservationsRepository.delete(it)
    }
  }
}
