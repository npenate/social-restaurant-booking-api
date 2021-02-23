package com.nelo.socialrestaurant.support.openapi

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Operation(summary = "Get restaurants available")
@ApiResponses(
  value = [
    ApiResponse(responseCode = "200", description = "Ok", content = [Content()]),
    ApiResponse(responseCode = "400", description = "Bad Request"),
    ApiResponse(
      responseCode = "404", description = "Not Found",
      content = [Content(
        mediaType = "application/json",
        examples = [ExampleObject(
          value = "Diner not found"
        )]
      )]
    ),
    ApiResponse(
      responseCode = "409", description = "Conflict",
      content = [Content(
        mediaType = "application/json",
        examples = [ExampleObject(
          value = "Some diner has an overlapping reservation"
        )]
      )]
    )
  ]
)
annotation class GetAvailabilityDocumentation
