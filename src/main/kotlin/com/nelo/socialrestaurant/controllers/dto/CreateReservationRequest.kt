package com.nelo.socialrestaurant.controllers.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

class CreateReservationRequest(
  @get: NotEmpty
  val dinerIds: List<@Valid UUID>,

  @get: Valid
  val tableId: UUID,

  @get: Valid
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  val scheduleAt: LocalDateTime
)
