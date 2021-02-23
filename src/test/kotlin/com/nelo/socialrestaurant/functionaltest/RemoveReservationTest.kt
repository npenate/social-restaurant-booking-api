package com.nelo.socialrestaurant.functionaltest

import com.fasterxml.jackson.databind.ObjectMapper
import com.nelo.socialrestaurant.controllers.dto.CreateReservationRequest
import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.models.entities.Reservation
import com.nelo.socialrestaurant.models.entities.Restaurant
import com.nelo.socialrestaurant.models.entities.Table
import com.nelo.socialrestaurant.repositories.DinersRepository
import com.nelo.socialrestaurant.repositories.FoodClassificationsRepository
import com.nelo.socialrestaurant.repositories.ReservationsHistoryRepository
import com.nelo.socialrestaurant.repositories.ReservationsRepository
import com.nelo.socialrestaurant.repositories.RestaurantsRepository
import com.nelo.socialrestaurant.repositories.TablesRepository
import com.nelo.socialrestaurant.testherlpers.builders.DinerBuilder
import com.nelo.socialrestaurant.testherlpers.builders.FoodClassificationBuilder
import com.nelo.socialrestaurant.testherlpers.builders.ReservationBuilder
import com.nelo.socialrestaurant.testherlpers.builders.RestaurantBuilder
import com.nelo.socialrestaurant.testherlpers.builders.TableBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class RemoveReservationTest {
  @Autowired
  private lateinit var foodClassificationsRepository: FoodClassificationsRepository
  @Autowired
  private lateinit var dinersRepository: DinersRepository
  @Autowired
  private lateinit var restaurantsRepository: RestaurantsRepository
  @Autowired
  private lateinit var reservationsRepository: ReservationsRepository
  @Autowired
  private lateinit var tablesRepository: TablesRepository
  @Autowired
  private lateinit var reservationsHistoryRepository: ReservationsHistoryRepository
  @Autowired
  private lateinit var mockMvc: MockMvc
  @Autowired
  private lateinit var mapper: ObjectMapper

  lateinit var reservation: Reservation

  @BeforeEach
  fun setUp() {
    reservationsHistoryRepository.deleteAll()
    reservationsRepository.deleteAll()
    foodClassificationsRepository.deleteAll()
    dinersRepository.deleteAll()
    tablesRepository.deleteAll()
    restaurantsRepository.deleteAll()

    var foodClassifications = listOf(
      FoodClassificationBuilder().name("FOOD").build(),
      FoodClassificationBuilder().name("PALEO").build(),
      FoodClassificationBuilder().name("VEGAN").build()
    )
    foodClassificationsRepository.saveAll(foodClassifications)

    val diners = listOf(
      DinerBuilder().restrictions(setOf(foodClassifications[0])).build(),
      DinerBuilder().restrictions(setOf(foodClassifications[0], foodClassifications[1])).build()
    )
    dinersRepository.saveAll(diners)

    var restaurants = listOf(
      RestaurantBuilder()
        .endorsements(setOf(foodClassifications[0], foodClassifications[1]))
        .zipCode(diners[0].zipCode).build(),
      RestaurantBuilder().endorsements(setOf(foodClassifications[0], foodClassifications[1]))
        .zipCode(diners[1].zipCode).build()
    )
    restaurantsRepository.saveAll(restaurants)

    val tables = listOf(
      TableBuilder().restaurant(restaurants[0]).zipCode(diners[0].zipCode).build(),
      TableBuilder().restaurant(restaurants[0]).zipCode(diners[0].zipCode).build(),
      TableBuilder().restaurant(restaurants[1]).zipCode(diners[1].zipCode).build(),
      TableBuilder().restaurant(restaurants[1]).zipCode(diners[1].zipCode).build()
    )
    tablesRepository.saveAll(tables)

    reservation = ReservationBuilder().diners(diners.toSet()).table(tables[0]).build()
    reservationsRepository.save(reservation)
  }

  @Test
  fun `Remove efectively a reservation`() {
    removeReservation(reservation.id)
      .andExpect(status().isNoContent)

    assert(reservationsRepository.findById(reservation.id).isEmpty)
    assert(reservationsHistoryRepository.findById(reservation.id).isPresent)
  }

  private fun removeReservation(
    reservationId: UUID
  ): ResultActions {
    return mockMvc.perform(
      MockMvcRequestBuilders.delete("/api/v1/reservations/$reservationId")
    )
  }
}
