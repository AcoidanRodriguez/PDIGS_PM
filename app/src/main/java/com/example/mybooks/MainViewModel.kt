package com.example.mybooks


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybooks.data.User
import com.example.mybooks.data.Project
import com.example.mybooks.data.UsersRepository
import com.example.mybooks.data.ProjectsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val usersRepository: UsersRepository, private val projectsRepository: ProjectsRepository) : ViewModel() {

    fun insertUser(id: Int, username: String, password: String, email: String) {
        viewModelScope.launch {
            usersRepository.insertUser(User(id, username, password, email))
        }
    }

    fun getAllUsers() {
        viewModelScope.launch {
            usersRepository.getAllUsersStream()
        }
    }

    fun getTotalUsers() {
        viewModelScope.launch {
            usersRepository.getTotalUsers()
        }
    }

    fun getUser(username: String) {
        viewModelScope.launch {
            usersRepository.getUserStream(username)
        }
    }

    fun getActiveUser(): User{
        return usersRepository.getActiveUser()
    }

    fun activateUser(user: User) {
        viewModelScope.launch {
            val updatedUser = user.copy(isActive = 1)
            usersRepository.updateUser(updatedUser)
        }
    }

    fun deactivateUser(user: User) {
        viewModelScope.launch {
            val updatedUser = user.copy(isActive = 0)
            usersRepository.updateUser(updatedUser)
        }
    }

    fun insertProject(project: Project) {
        viewModelScope.launch {
            projectsRepository.insertProject(project)
        }
    }

    fun deleteProject(project: Project) {
        viewModelScope.launch {
            projectsRepository.deleteProject(project)
        }
    }

    fun getAllProjects(): Flow<List<Project>> {
        return projectsRepository.getAllProjectsStream()
    }

}