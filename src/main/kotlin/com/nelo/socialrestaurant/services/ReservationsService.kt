package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.exceptions.OverlappingReservationException
import com.nelo.socialrestaurant.exceptions.PastDatatimeException
import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.models.entities.Reservation
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class ReservationsService(
  val dinersService: DinersService,
  val tablesService: TablesService,
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

  fun makeReservation(dinerIds: Collection<UUID>, tableId: UUID, scheduleAt: LocalDateTime): Reservation {
    if (scheduleAt < LocalDateTime.now()) {
      throw PastDatatimeException("The datetime provided is expired")
    }
    val start = scheduleAt.minusHours(Reservation.RESERVATION_DURATION_HOURS)
    val end = scheduleAt.plusHours(Reservation.RESERVATION_DURATION_HOURS)
    val table = tablesService.find(tableId)
    val diners = dinersService.find(dinerIds)

    if (tablesService.isAlreadyReserved(table, start, end) || existsOverlappingReservations(diners, start, end)) {
      throw OverlappingReservationException("There is an overlapping reservation")
    }
    return reservationsRepository.save(Reservation(
      id = UUID.randomUUID(),
      diners = diners.toSet(),
      table = table,
      scheduledAt = scheduleAt)
    )
  }
}
