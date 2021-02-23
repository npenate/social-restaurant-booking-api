package com.nelo.socialrestaurant.services.availabilitystrategy

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.entities.Table
import com.nelo.socialrestaurant.repositories.TablesRepository
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

class SingleTableStrategy(
  private val tablesRepository: TablesRepository
) : AvailabilitySearchBehavior {
  override fun findAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Pageable
  ): Collection<Table> {
    val endorsements = diners.map { it.restrictions }.reduce { acc, set -> acc.plus(set) }
    val zipCodes = diners.map { it.zipCode }

    return tablesRepository.findAvailableTables(
      endorsements = endorsements.map { it.id },
      zipCodes = zipCodes.joinToString(","),
      tablesCapacity = diners.size.toByte(),
      start = scheduleAt.minusHours(2),
      end = scheduleAt.plusHours(2),
      page = if (page.isPaged) page.pageNumber else 0,
      pageSize = if (page.isPaged) page.pageSize else 0
    )
  }
}
