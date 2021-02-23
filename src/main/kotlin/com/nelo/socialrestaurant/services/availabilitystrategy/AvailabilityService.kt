package com.nelo.socialrestaurant.services.availabilitystrategy

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.repositories.TablesRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AvailabilityService(
  val tablesRepository: TablesRepository
) {
  var availabilitySearchBehavior: AvailabilitySearchBehavior = SingleTableStrategy(tablesRepository)

  fun findAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Pageable
  ) = availabilitySearchBehavior.findAvailableTables(diners, scheduleAt, page)
}
