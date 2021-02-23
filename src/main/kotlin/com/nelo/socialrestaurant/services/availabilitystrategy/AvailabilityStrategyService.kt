package com.nelo.socialrestaurant.services.availabilitystrategy

import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.repositories.TablesRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AvailabilityStrategyService(
  val tablesRepository: TablesRepository
) {
  var availabilitySearchBehavior: AvailabilitySearchBehavior = SingleTableStrategy(tablesRepository)

  fun findAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Int,
    pageSize: Int
  ) = availabilitySearchBehavior.findAvailableTables(diners, scheduleAt, page, pageSize)
}
