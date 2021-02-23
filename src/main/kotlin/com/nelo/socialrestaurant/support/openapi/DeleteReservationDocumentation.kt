package com.nelo.socialrestaurant.support.openapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Operation(summary = "Delete a reservation")
@ApiResponses(
  value = [
    ApiResponse(responseCode = "204", description = "No Content"),
    ApiResponse(responseCode = "400", description = "Bad Request"),
    ApiResponse(
      responseCode = "404", description = "Not Found",
      content = [Content(
        mediaType = "application/json",
        examples = [ExampleObject(
          value = "Reservation not found"
        )]
      )]
    )
  ]
)
annotation class DeleteReservationDocumentation
