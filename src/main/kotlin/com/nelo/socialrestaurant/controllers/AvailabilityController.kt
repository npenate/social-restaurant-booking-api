package com.nelo.socialrestaurant.controllers

import com.nelo.socialrestaurant.controllers.dto.AvailableRestaurantResponse
import com.nelo.socialrestaurant.presenters.AvailableRestaurantsPresenter
import com.nelo.socialrestaurant.services.AvailabilityService
import com.nelo.socialrestaurant.support.openapi.GetAvailabilityDocumentation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.UUID

@Tag(
  name = "Availability",
  description = "Operations of availability"
)
@RestController
@RequestMapping("/api/v1/availability")
class AvailabilityController(
  val availableRestaurantsPresenter: AvailableRestaurantsPresenter,
  val availabilityService: AvailabilityService
) {

  @GetAvailabilityDocumentation
  @GetMapping
  fun getAvailability(
    @RequestParam dinerId: List<UUID>,
    @RequestParam page: Int?,
    @RequestParam pageSize: Int?,
    @RequestParam("datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) datetime: LocalDateTime
  ): ResponseEntity<Collection<AvailableRestaurantResponse>> {
    val availableTables = availabilityService.getAvailableTables(dinerId, datetime, page, pageSize)
    return ResponseEntity.ok(availableRestaurantsPresenter.present(availableTables))
  }
}
