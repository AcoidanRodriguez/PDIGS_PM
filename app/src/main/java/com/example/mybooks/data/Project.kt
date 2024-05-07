package com.example.mybooks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "projects_table")
data class Project(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val title: String,
    val start_date: String,
    val end_date: String,
    val userStories: List<String> = emptyList(),
    val userStoriesForSprint:List<String> = emptyList()
)
