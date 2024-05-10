package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineProjectsRepository(private val projectDao: ProjectDao): ProjectsRepository {
    override fun getAllProjectsStream(): Flow<List<Project>> = projectDao.getAllProjects()

    override fun getTotalProjectsStream(): Int = projectDao.getTotalProjects()

    override fun getProjectStream(username: String): Flow<Project> = projectDao.getUser(username)

    override suspend fun insertProject(item: Project) = projectDao.insert(item)

    override suspend fun deleteProject(item: Project) = projectDao.delete(item)

    override suspend fun updateProject(item: Project) = projectDao.update(item)
    override fun getUserStoriesForProjectID(projectID: Int): Flow<List<String>> = projectDao.getUserStoriesForProject(projectID)

    override suspend fun addUserStoryToProject(projectId: Int, userStory: String) {
        val currentProject = projectDao.getProjectById(projectId) ?: return
        val userStories = currentProject.userStories.toMutableList()
        userStories.add(userStory)
        val updatedProject = currentProject.copy(userStories = userStories)
        projectDao.update(updatedProject)
    }

    override suspend fun getProjectById(projectID: Int): Project? = projectDao.getProjectById(projectID)
    override fun getUserStoriesForSprintForProject(projectId: Int): Flow<List<String>> {
        return projectDao.getUserStoriesForSprintForProject(projectId)
    }

    override suspend fun addUserStoryForSprintToProject(projectId: Int, userStory: String) {
        val currentProject = projectDao.getProjectById(projectId) ?: return
        val us = currentProject.userStoriesForSprint.toMutableList()
        us.add(userStory)
        val updatedProject = currentProject.copy(userStoriesForSprint = us)
        projectDao.update(updatedProject)
    }

}