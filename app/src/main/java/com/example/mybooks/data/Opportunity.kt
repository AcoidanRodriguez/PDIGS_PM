package com.example.mybooks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "opportunities_table")
data class Opportunity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String
)
