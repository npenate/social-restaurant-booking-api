package com.nelo.socialrestaurant.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(
  name = "Health Check",
  description = "API health"
)

@RestController
class HealthController {
  @Operation(summary = "Check if the service api is healthy")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "The service is healthy.")]
  )
  @GetMapping("/health")
  fun health(): ResponseEntity<String> {
    return ResponseEntity("healthy", HttpStatus.OK)
  }
}
