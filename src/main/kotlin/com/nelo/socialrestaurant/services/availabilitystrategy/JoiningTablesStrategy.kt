package com.nelo.socialrestaurant.services.availabilitystrategy

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.entities.Table
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

class JoiningTablesStrategy : AvailabilitySearchBehavior {
  override fun findAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Pageable
  ): Collection<Table> {
    TODO("Not yet implemented")
  }
}
