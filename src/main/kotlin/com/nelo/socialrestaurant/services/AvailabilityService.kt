package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.exceptions.OverlappingReservationException
import com.nelo.socialrestaurant.exceptions.PastDatatimeException
import com.nelo.socialrestaurant.models.entities.Table
import com.nelo.socialrestaurant.services.availabilitystrategy.AvailabilityStrategyService
import org.springframework.data.domain.Pageable
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
    private const val RESERVATION_DURATION_HOURS = 2L
  }

  fun getAvailableTables(
    dinerIds: Collection<UUID>,
    scheduleAt: LocalDateTime,
    page: Int?,
    pageSize: Int?
  ): Collection<Table> {
    val diners = dinersService.find(dinerIds)
    if (reservationsService.existsOverlappingReservations(
        diners,
        scheduleAt.minusHours(RESERVATION_DURATION_HOURS),
        scheduleAt.plusHours(RESERVATION_DURATION_HOURS))
    ) {
      throw OverlappingReservationException("Some diner has an overlapping reservation")
    }
    if (scheduleAt < LocalDateTime.now()) {
      throw PastDatatimeException("The datetime provided expired")
    }
    return availabilityStrategyService.findAvailableTables(diners, scheduleAt, page ?: 0, pageSize ?: 20)
  }
}
