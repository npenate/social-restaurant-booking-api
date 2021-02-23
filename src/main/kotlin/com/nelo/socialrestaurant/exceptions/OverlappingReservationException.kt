package com.nelo.socialrestaurant.exceptions

class OverlappingReservationException(
    private val s: String,
    private val c: Throwable? = null
) : Exception(s, c)
