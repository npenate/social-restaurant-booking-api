package com.nelo.socialrestaurant.controllers

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
  name = "Reservations",
  description = "Operations over Reservations"
)
@RestController
@RequestMapping("/api/v1/reservations")
class ReservationsController
