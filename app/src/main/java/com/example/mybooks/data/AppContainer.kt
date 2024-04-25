package com.example.mybooks.data

import android.content.Context

interface AppContainer {
    val usersRepository: UsersRepository
    val projectsRepository: ProjectsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val usersRepository: UsersRepository by lazy {
        val database = UserDatabase.getDatabase(context)
        OfflineUsersRepository(database.userDao())
    }

    override val projectsRepository: ProjectsRepository by lazy {
        OfflineProjectsRepository(ProjectDatabase.getDatabase(context).projectDao())
    }
}