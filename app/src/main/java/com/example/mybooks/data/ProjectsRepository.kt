package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface ProjectsRepository {
    fun getAllProjectsStream(): Flow<List<Project>>

    fun getTotalProjectsStream(): Int

    fun getProjectStream(user: String): Flow<Project>

    suspend fun insertProject(item: Project)

    suspend fun deleteProject(item: Project)

    suspend fun updateProject(item: Project)

    // Nuevo método para obtener las historias de usuario de un proyecto por su ID
    fun getUserStoriesForProjectID(projectID: Int): Flow<List<String>>

    // Nuevo método para agregar una historia de usuario a un proyecto existente
    suspend fun addUserStoryToProject(projectId: Int, userStory: String)

    suspend fun getProjectById(projectID: Int): Project?

}