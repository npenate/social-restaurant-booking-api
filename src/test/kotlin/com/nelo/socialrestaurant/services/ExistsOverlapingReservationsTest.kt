package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.entities.Reservation
import com.nelo.socialrestaurant.entities.Table
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import com.nelo.socialrestaurant.testherlpers.builders.DinerBuilder
import com.nelo.socialrestaurant.testherlpers.builders.ReservationBuilder
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import java.util.UUID


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class ExistsOverlappingReservationsTest {
  @MockkBean
  private lateinit var reservationsRepository: ReservationsRepository
  @Autowired
  private lateinit var overlappingReservationsService: ReservationService

  @Test
  fun `The provided diners do not have overlapping reservations`() {
    every { reservationsRepository.findByDinersInAndScheduledAtBetween(any(), any(), any()) } answers {
      emptyList()
    }

    val diners = listOf(
      DinerBuilder().build(),
      DinerBuilder().build(),
      DinerBuilder().build()
    )

    val existsOverlappingReservations = overlappingReservationsService.existsOverlappingReservations(
      diners,
      LocalDateTime.now().minusHours(2),
      LocalDateTime.now().plusHours(2)
    )
    assert(existsOverlappingReservations == false)
  }

  @Test
  fun `The provided diners have overlapping reservations`() {
    every { reservationsRepository.findByDinersInAndScheduledAtBetween(any(), any(), any()) } answers {
      listOf(ReservationBuilder().build())
    }

    val diners = listOf(
      DinerBuilder().build(),
      DinerBuilder().build(),
      DinerBuilder().build()
    )

    val existsOverlappingReservations = overlappingReservationsService.existsOverlappingReservations(
      diners,
      LocalDateTime.now().minusHours(2),
      LocalDateTime.now().plusHours(2)
    )
    assert(existsOverlappingReservations == true)
  }
}
