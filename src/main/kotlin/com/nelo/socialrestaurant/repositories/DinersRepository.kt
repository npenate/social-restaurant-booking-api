package com.nelo.socialrestaurant.repositories

import com.nelo.socialrestaurant.entities.Diner
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DinersRepository : CrudRepository<Diner, UUID>
