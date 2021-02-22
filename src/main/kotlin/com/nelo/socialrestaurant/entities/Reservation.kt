package com.nelo.socialrestaurant.entities

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity(name = "reservations")
data class Reservation(
  @Id
  @GeneratedValue(generator = "UUID")
  var id: UUID,
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "reservations_diners",
    joinColumns=[JoinColumn(name = "reservation_id")],
    inverseJoinColumns=[JoinColumn(name = "diner_id")]
  )
  var diners: Set<Diner>,
  @ManyToOne(fetch = FetchType.LAZY)
  var table: Table,
  var scheduledAt: LocalDateTime,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)
