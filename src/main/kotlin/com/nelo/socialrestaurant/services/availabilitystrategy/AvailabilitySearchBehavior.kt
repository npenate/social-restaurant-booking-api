package com.nelo.socialrestaurant.services.availabilitystrategy

import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.models.entities.Table
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

interface AvailabilitySearchBehavior {
  fun findAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Int,
    pageSize: Int
  ): Collection<Table>
}
