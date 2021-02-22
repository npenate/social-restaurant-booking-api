package com.nelo.socialrestaurant.repositories

import com.nelo.socialrestaurant.entities.Diner
import com.nelo.socialrestaurant.entities.Reservation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.UUID

@Repository
interface ReservationsRepository : CrudRepository<Reservation, UUID> {
  fun findByDinersInAndScheduledAtBetween(
    diners: Collection<Diner>,
    start: LocalDateTime,
    end: LocalDateTime
  ): Collection<Reservation>
}
