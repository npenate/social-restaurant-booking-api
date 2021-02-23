package com.nelo.socialrestaurant.exceptions

class EntityNotFoundException(
    private val s: String,
    private val c: Throwable? = null
) : Exception(s, c)
