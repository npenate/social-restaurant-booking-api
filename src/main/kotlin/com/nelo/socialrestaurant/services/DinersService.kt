package com.nelo.socialrestaurant.services

import com.nelo.socialrestaurant.exceptions.EntityNotFoundException
import com.nelo.socialrestaurant.models.entities.Diner
import com.nelo.socialrestaurant.repositories.DinersRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DinersService(
  val dinersRepository: DinersRepository
) {

  fun find(ids: Collection<UUID>): List<Diner> {
    val diners = dinersRepository.findAllById(ids)
    if (diners.none())
      throw EntityNotFoundException("Diner not found")
    return diners.toList()
  }
}
