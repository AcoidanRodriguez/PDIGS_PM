package com.example.mybooks.data

import kotlinx.coroutines.flow.Flow

class OfflineProjectsRepository(private val projectDao: ProjectDao): ProjectsRepository {
    override fun getAllProjectsStream(): Flow<List<Project>> = projectDao.getAllProjects()

    override fun getTotalProjectsStream(): Int = projectDao.getTotalProjects()

    override fun getProjectStream(username: String): Flow<Project> = projectDao.getUser(username)

    override suspend fun insertProject(item: Project) = projectDao.insert(item)

    override suspend fun deleteProject(item: Project) = projectDao.delete(item)

    override suspend fun updateProject(item: Project) = projectDao.update(item)

}