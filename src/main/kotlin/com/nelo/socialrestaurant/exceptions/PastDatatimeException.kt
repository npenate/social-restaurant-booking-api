package com.nelo.socialrestaurant.exceptions

class PastDatatimeException(
    private val s: String,
    private val c: Throwable? = null
) : Exception(s, c)
