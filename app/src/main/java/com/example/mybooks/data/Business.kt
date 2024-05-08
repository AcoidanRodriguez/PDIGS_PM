package com.example.mybooks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "Businesses_table")
data class Business(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String
)