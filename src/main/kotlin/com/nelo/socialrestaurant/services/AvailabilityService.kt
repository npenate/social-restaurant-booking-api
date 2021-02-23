package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.exceptions.OverlappingReservationException
import com.nelo.socialrestaurant.exceptions.PastDatatimeException
import com.nelo.socialrestaurant.models.entities.Reservation
import com.nelo.socialrestaurant.models.entities.Table
import com.nelo.socialrestaurant.services.availabilitystrategy.AvailabilityStrategyService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class AvailabilityService(
  private val availabilityStrategyService: AvailabilityStrategyService,
  private val reservationsService: ReservationsService,
  private val dinersService: DinersService
) {

  companion object {
    private const val DEFAULT_PAGE = 0
    private const val DEFAULT_PAGE_SIZE = 20
  }

  fun getAvailableTables(dinerIds: Collection<UUID>, scheduleAt: LocalDateTime,
                         page: Int? = null, pageSize: Int? = null): Collection<Table> {
    if (scheduleAt < LocalDateTime.now()) {
      throw PastDatatimeException("The datetime provided is expired")
    }
    val diners = dinersService.find(dinerIds)
    if (reservationsService.existsOverlappingReservations(
        diners,
        scheduleAt.minusHours(Reservation.RESERVATION_DURATION_HOURS),
        scheduleAt.plusHours(Reservation.RESERVATION_DURATION_HOURS))
    ) {
      throw OverlappingReservationException("Some diner has an overlapping reservation")
    }
    return availabilityStrategyService.findAvailableTables(diners, scheduleAt,
      page ?: DEFAULT_PAGE, pageSize ?: DEFAULT_PAGE_SIZE)
  }
}
