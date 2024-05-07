package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

interface ProjectsRepository {
    fun getAllProjectsStream(): Flow<List<Project>>

    fun getTotalProjectsStream(): Int

    fun getProjectStream(user: String): Flow<Project>

    suspend fun insertProject(item: Project)

    suspend fun deleteProject(item: Project)

    suspend fun updateProject(item: Project)

    fun getUserStoriesForProjectID(projectID: Int): Flow<List<String>>

    suspend fun addUserStoryToProject(projectId: Int, userStory: String)

    suspend fun getProjectById(projectID: Int): Project?

    fun getUserStoriesForSprintForProject(projectId: Int): Flow<List<String>>

    suspend fun addUserStoryForSprintToProject(projectId: Int, userStory: String)

}