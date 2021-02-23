package com.nelo.socialrestaurant.controllers

import com.nelo.socialrestaurant.controllers.dto.CreateReservationRequest
import com.nelo.socialrestaurant.controllers.dto.CreatedReservationResponse
import com.nelo.socialrestaurant.services.ReservationsService
import com.nelo.socialrestaurant.support.openapi.MakeReservationDocumentation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(
  name = "Reservations",
  description = "Operations over Reservations"
)
@RestController
@RequestMapping("/api/v1/reservations")
class ReservationsController(
  val reservationsService: ReservationsService
) {

  @MakeReservationDocumentation
  @PostMapping
  fun makeReservation(
    @RequestBody @Valid createReservationRequest: CreateReservationRequest
  ) = ResponseEntity.ok(
    CreatedReservationResponse.fromEntity(
      reservationsService.makeReservation(
        createReservationRequest.dinerIds,
        createReservationRequest.tableId,
        createReservationRequest.scheduleAt
      )
    )
  )
}
