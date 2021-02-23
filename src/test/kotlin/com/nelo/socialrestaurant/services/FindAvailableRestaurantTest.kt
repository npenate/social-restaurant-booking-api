package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.entities.Table
import com.nelo.socialrestaurant.repositories.TablesRepository
import com.nelo.socialrestaurant.testherlpers.builders.DinerBuilder
import com.nelo.socialrestaurant.testherlpers.builders.TableBuilder
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class FindAvailableRestaurantTest {
  @MockkBean
  private lateinit var tablesRepository: TablesRepository
  @Autowired
  private lateinit var reservationService: ReservationService

  @Test
  fun `Get a non empty list of available tables`() {
    every { tablesRepository.findAvailableTables(any(), any(), any(), any(), any(), any(), any()) } answers {
      arrayListOf(TableBuilder().build())
    }

    val diners = mutableListOf(
      DinerBuilder().build(),
      DinerBuilder().build(),
      DinerBuilder().build()
    )

    val availableTables = reservationService.getAvailableTables(
      diners,
      LocalDateTime.now(),
      Pageable.unpaged()
    )
    assert(availableTables.isNotEmpty())
  }

  @Test
  fun `Get an empty list of available tables`() {
    every { tablesRepository.findAvailableTables(any(), any(), any(), any(), any(), any(), any()) } answers {
      emptyList()
    }

    val diners = mutableListOf(
      DinerBuilder().build(),
      DinerBuilder().build(),
      DinerBuilder().build()
    )

    val availableTables = reservationService.getAvailableTables(
      diners,
      LocalDateTime.now(),
      Pageable.unpaged()
    )
    assert(availableTables.isEmpty())
  }
}
