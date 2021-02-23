package com.nelo.socialrestaurant.services.availabilitystrategy

import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.models.entities.Table
import com.nelo.socialrestaurant.repositories.TablesRepository
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime
import java.util.UUID

class SingleTableStrategy(
  private val tablesRepository: TablesRepository
) : AvailabilitySearchBehavior {
  override fun findAvailableTables(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Int,
    pageSize: Int
  ): Collection<Table> {
    val endorsements = mutableSetOf<UUID>()
    val zipCodes = mutableSetOf<String>()
    diners.forEach { diner ->
      diner.restrictions.forEach { endorsements.add(it.id) }; zipCodes.add(diner.zipCode) }

    return tablesRepository.findAvailableTables(
      endorsements = endorsements,
      zipCodes = zipCodes,
      tablesCapacity = diners.size.toByte(),
      start = scheduleAt.minusHours(2),
      end = scheduleAt.plusHours(2),
      page = page,
      pageSize = pageSize
    )
  }
}
