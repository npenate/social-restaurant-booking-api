package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.repositories.DinersRepository
import com.nelo.socialrestaurant.repositories.TablesRepository
import com.nelo.socialrestaurant.services.availabilitystrategy.AvailabilityStrategyService
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
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class FindAvailableTablesTest {
  @MockkBean
  private lateinit var availabilityStrategyService: AvailabilityStrategyService
  @MockkBean
  private lateinit var reservationsService: ReservationsService
  @MockkBean
  private lateinit var dinersService: DinersService
  @Autowired
  private lateinit var availabilityService: AvailabilityService


  @Test
  fun `Get a non empty list of available tables`() {
    every { availabilityStrategyService.findAvailableTables(any(), any(), any(), any()) } answers {
      arrayListOf(TableBuilder().build())
    }
    every { dinersService.find(any()) } answers {
      arrayListOf(DinerBuilder().build())
    }
    every { reservationsService.existsOverlappingReservations(any(), any(), any()) } answers {
      false
    }

    val dinerIds = listOf(
      UUID.randomUUID(),
      UUID.randomUUID(),
      UUID.randomUUID()
    )

    val availableTables = availabilityService.getAvailableTables(
      dinerIds,
      LocalDateTime.now().plusHours(2)
    )
    assert(availableTables.isNotEmpty())
  }

  @Test
  fun `Get an empty list of available tables`() {
    every { availabilityStrategyService.findAvailableTables(any(), any(), any(), any()) } answers {
      arrayListOf()
    }
    every { dinersService.find(any()) } answers {
      arrayListOf(DinerBuilder().build())
    }
    every { reservationsService.existsOverlappingReservations(any(), any(), any()) } answers {
      false
    }

    val dinerIds = listOf(
      UUID.randomUUID(),
      UUID.randomUUID(),
      UUID.randomUUID()
    )

    val availableTables = availabilityService.getAvailableTables(
      dinerIds,
      LocalDateTime.now().plusHours(2)
    )
    assert(availableTables.isEmpty())
  }
}
