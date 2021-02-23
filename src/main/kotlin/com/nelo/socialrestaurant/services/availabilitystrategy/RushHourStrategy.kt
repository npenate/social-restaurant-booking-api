package com.nelo.socialrestaurant.services.availabilitystrategy

import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.models.entities.Table
import com.nelo.socialrestaurant.repositories.TablesRepository
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

class RushHourStrategy(
  private val tablesRepository: TablesRepository
) : AvailabilitySearchBehavior {
  override fun findAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Int,
    pageSize: Int
  ): Collection<Table> {
    TODO("Not yet implemented")
  }
}
