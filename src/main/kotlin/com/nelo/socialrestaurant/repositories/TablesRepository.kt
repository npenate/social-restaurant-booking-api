package com.nelo.socialrestaurant.repositories

import com.nelo.socialrestaurant.models.entities.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.UUID

@Repository
interface TablesRepository : JpaRepository<Table, UUID> {
  @Suppress("LongParameterList", "LongMethod")
  @Query(
    "WITH preselected_tables as " +
      "(SELECT * FROM tables WHERE zip_code IN (:zipcodes) AND capacity >= :tables_capacity), " +
          "filtered_restaurant_ids as (SELECT DISTINCT r.id FROM restaurants r " +
          "JOIN restaurants_food_classifications rfc ON r.id = rfc.restaurant_id " +
              "WHERE rfc.food_classification_id IN (:endorsements) AND r.zip_code IN(:zipcodes)), " +
          "reserved_table_ids as (SELECT pt.id FROM preselected_tables pt JOIN reservations r " +
              "ON pt.id = r.table_id WHERE r.scheduled_at > :start AND r.scheduled_at < :end) " +
      "SELECT * FROM preselected_tables pt WHERE pt.restaurant_id IN (SELECT * FROM filtered_restaurant_ids) " +
          "AND pt.id NOT IN (SELECT * FROM reserved_table_ids) limit :page_size offset :page", nativeQuery = true)
  fun findAvailableTables(
    @Param("endorsements") endorsements: Set<UUID>,
    @Param("zipcodes") zipCodes: Set<String>,
    @Param("tables_capacity") tablesCapacity: Byte,
    @Param("start") start: LocalDateTime,
    @Param("end") end: LocalDateTime,
    @Param("page") page: Int,
    @Param("page_size") pageSize: Int
  ): List<Table>
}
