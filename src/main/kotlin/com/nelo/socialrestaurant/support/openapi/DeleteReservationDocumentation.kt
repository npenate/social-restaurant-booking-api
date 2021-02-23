package com.nelo.socialrestaurant.support.openapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Operation(summary = "Create a reservation")
@ApiResponses(
  value = [
    ApiResponse(responseCode = "200", description = "Ok", content = [Content()]),
    ApiResponse(responseCode = "400", description = "Bad Request"),
    ApiResponse(
      responseCode = "404", description = "Not Found",
      content = [Content(
        mediaType = "application/json",
        examples = [ExampleObject(
          value = "Table not found"
        )]
      )]
    ),
    ApiResponse(
      responseCode = "409", description = "Conflict",
      content = [Content(
        mediaType = "application/json",
        examples = [ExampleObject(
          value = "The requested table has an overlapping reservation"
        )]
      )]
    )
  ]
)
annotation class MakeReservationDocumentation
