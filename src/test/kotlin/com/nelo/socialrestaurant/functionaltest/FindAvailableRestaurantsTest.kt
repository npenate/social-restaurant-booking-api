package com.nelo.socialrestaurant.functionaltest

import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.repositories.DinersRepository
import com.nelo.socialrestaurant.repositories.FoodClassificationsRepository
import com.nelo.socialrestaurant.repositories.RestaurantsRepository
import com.nelo.socialrestaurant.repositories.TablesRepository
import com.nelo.socialrestaurant.testherlpers.builders.DinerBuilder
import com.nelo.socialrestaurant.testherlpers.builders.FoodClassificationBuilder
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
internal class FindAvailableRestaurantsTest {
  @Autowired
  private lateinit var foodClassificationsRepository: FoodClassificationsRepository
  @Autowired
  private lateinit var dinersRepository: DinersRepository
  @Autowired
  private lateinit var restaurantsRepository: RestaurantsRepository
  @Autowired
  private lateinit var tablesRepository: TablesRepository
  @Autowired
  private lateinit var mockMvc: MockMvc

  var diners = emptyList<Diner>()

  @BeforeEach
  fun setUp() {
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

    diners = listOf(
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

    var tables = listOf(
      TableBuilder().restaurant(restaurants[0]).zipCode(diners[0].zipCode).build(),
      TableBuilder().restaurant(restaurants[0]).zipCode(diners[0].zipCode).build(),
      TableBuilder().restaurant(restaurants[1]).zipCode(diners[1].zipCode).build(),
      TableBuilder().restaurant(restaurants[1]).zipCode(diners[1].zipCode).build()
    )
    tablesRepository.saveAll(tables)
  }

  @Test
  fun `Get a list of available restaurants`() {
    getAvailableRestaurants(listOf(diners[0].id, diners[1].id), LocalDateTime.now().plusHours(1), 0, 20)
      .andExpect(status().isOk)
      .andExpect(jsonPath("$[0].id").exists())

  }

  private fun getAvailableRestaurants(
    dinerIds: List<UUID>,
    datetime: LocalDateTime,
    page: Int,
    pageSize: Int
  ): ResultActions {
    val query = UriComponentsBuilder
      .newInstance()
      .queryParam("dinerId", dinerIds)
      .queryParam("datetime", datetime)
      .queryParam("page", page)
      .queryParam("size", pageSize)
      .build()

    return mockMvc.perform(
      MockMvcRequestBuilders.get("/api/v1/availability$query")
        .contentType(MediaType.APPLICATION_JSON)
    )
  }
}
