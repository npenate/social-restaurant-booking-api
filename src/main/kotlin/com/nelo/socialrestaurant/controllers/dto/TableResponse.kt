package com.nelo.socialrestaurant.controllers.dto

import com.nelo.socialrestaurant.entities.Table
import java.util.UUID

data class TableResponse(
  var id: UUID,
  var capacity: Byte,
  var description: String?
) {
  companion object {
    fun fromEntity(table: Table) = TableResponse(
      id = table.id,
      capacity = table.capacity,
      description = table.description
    )
  }
}
