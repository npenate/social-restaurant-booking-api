package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.exceptions.EntityNotFoundException
import com.nelo.socialrestaurant.models.entities.Table
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import com.nelo.socialrestaurant.repositories.TablesRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class TablesService(
  val reservationsRepository: ReservationsRepository,
  val tablesRepository: TablesRepository
) {

  fun isAlreadyReserved(
    table: Table,
    start: LocalDateTime,
    end: LocalDateTime
  ) = !reservationsRepository.findByTableAndScheduledAtBetween(table, start, end).isEmpty()

  fun find(id: UUID): Table = tablesRepository.findById(id).orElseThrow {
      EntityNotFoundException("Table not found")
  }
}
