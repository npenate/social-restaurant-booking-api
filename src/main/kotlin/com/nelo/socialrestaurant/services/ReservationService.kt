package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.entities.Restaurant
import com.nelo.socialrestaurant.entities.Table
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import com.nelo.socialrestaurant.repositories.TablesRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.collections.ArrayList

@Service
class ReservationService(
  val reservationsRepository: ReservationsRepository,
  val tablesRepository: TablesRepository
) {

  fun existsOverlappingReservations(
    diners: Collection<Diner>,
    start: LocalDateTime,
    end: LocalDateTime
  ): Boolean {
    val reservations = reservationsRepository.findByDinersInAndScheduledAtBetween(diners, start, end)
    return !reservations.isEmpty()
  }

  fun getAvailableRestaurants(
    diners: Collection<Diner>,
    scheduleAt: LocalDateTime,
    page: Pageable
  ): Collection<Restaurant> {
    val restrictions = diners.map { it.restrictions }.reduce { acc, set -> acc.plus(set) }
    val zipCodes = diners.map { it.zipCode }

    val tables = tablesRepository.findAvailableTables(
      endorsements = restrictions.map { it.id },
      zipCodes = zipCodes.joinToString(","),
      tablesCapacity = diners.size.toByte(),
      start = scheduleAt.minusHours(2),
      end = scheduleAt.plusHours(2),
      page = if (page.isPaged) page.pageNumber else 0,
      pageSize = if (page.isPaged) page.pageSize else 0
    )
    return groupTablesInRestaurants(tables)
  }

  private fun groupTablesInRestaurants(tables: ArrayList<Table>): Collection<Restaurant> {
    return emptyList()
  }
}
