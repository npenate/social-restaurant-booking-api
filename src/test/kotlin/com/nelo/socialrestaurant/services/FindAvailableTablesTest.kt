package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.repositories.DinersRepository
import com.nelo.socialrestaurant.repositories.TablesRepository
import com.nelo.socialrestaurant.testherlpers.builders.DinerBuilder
import com.nelo.socialrestaurant.testherlpers.builders.TableBuilder
import com.nelo.socialrestaurant.testherlpers.valuegenerators.StringValueGenerator
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class FindAvailableTablesTest {
  @MockkBean
  private lateinit var tablesRepository: TablesRepository
  @MockkBean
  private lateinit var dinersRepository: DinersRepository
  @Autowired
  private lateinit var availabilityService: AvailabilityService


  @Test
  fun `Get a non empty list of available tables`() {
    every { tablesRepository.findAvailableTables(any(), any(), any(), any(), any(), any(), any()) } answers {
      arrayListOf(TableBuilder().build())
    }
    every { dinersRepository.findAllById(any()) } answers {
      arrayListOf(DinerBuilder().build())
    }

    val dinerIds = listOf(
      UUID.randomUUID(),
      UUID.randomUUID(),
      UUID.randomUUID()
    )

    val availableTables = availabilityService.getAvailableTables(
      dinerIds,
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
    every { dinersRepository.findAllById(any()) } answers {
      arrayListOf(DinerBuilder().build())
    }

    val dinerIds = listOf(
      UUID.randomUUID(),
      UUID.randomUUID(),
      UUID.randomUUID()
    )

    val availableTables = availabilityService.getAvailableTables(
      dinerIds,
      LocalDateTime.now(),
      Pageable.unpaged()
    )
    assert(availableTables.isEmpty())
  }
}
