package com.nelo.socialrestaurant.exceptions.handlers

import com.nelo.socialrestaurant.exceptions.EntityNotFoundException
import com.nelo.socialrestaurant.exceptions.OverlappingReservationException
import com.nelo.socialrestaurant.exceptions.PastDatatimeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionsRestHandler : ResponseEntityExceptionHandler() {

  @ExceptionHandler(EntityNotFoundException::class)
  fun handleEntityNotFoundException(ex: EntityNotFoundException) =
    ResponseEntity(ex.message, HttpStatus.NOT_FOUND)

  @ExceptionHandler(OverlappingReservationException::class)
  fun handleOverlappingReservationException(ex: OverlappingReservationException) =
    ResponseEntity(ex.message, HttpStatus.CONFLICT)

  @ExceptionHandler(PastDatatimeException::class)
  fun handlePastDatatimeException(ex: PastDatatimeException) =
    ResponseEntity(ex.message, HttpStatus.UNPROCESSABLE_ENTITY)
}
