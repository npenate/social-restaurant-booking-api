package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.exceptions.OverlappingReservationException
import com.nelo.socialrestaurant.exceptions.PastDatatimeException
import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.models.entities.Reservation
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

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

  fun makeReservation(
    dinerIds: Collection<UUID>,
    tableId: UUID,
    scheduleAt: LocalDateTime
  ): Reservation {
    val start = scheduleAt.minusHours(Reservation.RESERVATION_DURATION_HOURS)
    val end = scheduleAt.plusHours(Reservation.RESERVATION_DURATION_HOURS)
    val diners = dinersService.find(dinerIds)
    if (existsOverlappingReservations(diners, start, end)) {
      throw OverlappingReservationException("Some diner has an overlapping reservation")
    }
    val table = tablesService.find(tableId)
    if(tablesService.isAlreadyReserved(table, start, end)){
      throw OverlappingReservationException("Table already reserved")
    }
    if (scheduleAt < LocalDateTime.now()) {
      throw PastDatatimeException("The datetime provided is expired")
    }
    val reservation = Reservation(
      id = UUID.randomUUID(),
      diners = diners.toSet(),
      table = table,
      scheduledAt = scheduleAt
    )
    return reservationsRepository.save(reservation)
  }
}
