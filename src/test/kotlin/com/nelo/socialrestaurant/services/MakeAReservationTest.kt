package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.models.entities.Reservation
import com.nelo.socialrestaurant.models.entities.Restaurant
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import com.nelo.socialrestaurant.testherlpers.builders.DinerBuilder
import com.nelo.socialrestaurant.testherlpers.builders.TableBuilder
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class MakeAReservationTest {
  @MockkBean
  private lateinit var dinersService: DinersService
  @MockkBean
  private lateinit var tablesService: TablesService
  @MockkBean
  private lateinit var reservationsRepository: ReservationsRepository
  @Autowired
  private lateinit var reservationsService: ReservationsService


  @Test
  fun `Create a successful reservation`() {
    val diners = listOf(DinerBuilder().build())
    every { dinersService.find(any()) } answers {
      diners
    }
    val table = TableBuilder().build()
    every { tablesService.find(any()) } answers {
      table
    }
    every { tablesService.isAlreadyReserved(any(), any(), any()) } answers {
      false
    }
    every { reservationsRepository.save(any<Reservation>()) }.returnsArgument(0)
    every { reservationsRepository.findByDinersInAndScheduledAtBetween(any(), any(), any()) } answers {
      emptyList()
    }

    val reservation = reservationsService.makeReservation(
      listOf(diners[0].id),
      table.id,
      LocalDateTime.now().plusHours(1))

    assert(reservation.table.id == table.id)
    assert(reservation.diners.size == diners.size)
    assert(reservation.diners.first().id == diners.first().id)
  }
}
