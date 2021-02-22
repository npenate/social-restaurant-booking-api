package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.testherlpers.builders.DinerBuilder
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
  @Autowired
  private lateinit var reservationService: ReservationService

  @Test
  fun `Get a non empty list of available restaurants`() {
    val diners = mutableListOf(
      DinerBuilder().build(),
      DinerBuilder().build(),
      DinerBuilder().build()
    )

    val availableRestaurants = reservationService.getAvailableRestaurants(
      diners,
      LocalDateTime.now(),
      Pageable.unpaged()
    )
    assert(availableRestaurants.isNotEmpty())
  }
}
