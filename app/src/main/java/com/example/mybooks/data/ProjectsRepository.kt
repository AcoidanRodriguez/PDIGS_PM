package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface ProjectsRepository {
    fun getAllProjectsStream(): Flow<List<Project>>

    fun getTotalProjectsStream(): Int

    fun getProjectStream(user: String): Flow<Project>

    suspend fun insertProject(item: Project)

    suspend fun deleteProject(item: Project)

    suspend fun updateProject(item: Project)
}