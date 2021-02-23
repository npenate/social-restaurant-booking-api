package com.nelo.socialrestaurant.services.availabilitystrategy

import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.models.entities.Table
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

class JoiningTablesStrategy : AvailabilitySearchBehavior {
  override fun findAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Int,
    pageSize: Int
  ): Collection<Table> {
    TODO("Not yet implemented")
  }
}
