package com.example.mybooks


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybooks.data.User
import com.example.mybooks.data.Project
import com.example.mybooks.data.UsersRepository
import com.example.mybooks.data.ProjectsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.mybooks.data.Business
import com.example.mybooks.data.BusinessRepository
import com.example.mybooks.data.Market
import com.example.mybooks.data.Income
import com.example.mybooks.data.Competitors
import com.example.mybooks.data.CompetitorsRepository
import com.example.mybooks.data.IncomeRepository
import com.example.mybooks.data.MarketRepository
import com.example.mybooks.data.Stakeholders
import com.example.mybooks.data.StakeholdersRepository

class MainViewModel(private val usersRepository: UsersRepository, private val projectsRepository: ProjectsRepository,private val businessRepository:BusinessRepository,private val marketRepository:MarketRepository,private val competitorsRepository: CompetitorsRepository,private val incomeRepository: IncomeRepository ,private val stakeholdersRepository: StakeholdersRepository,) : ViewModel() {

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

    fun getAllBusiness(): Flow<List<Business>> {
        return businessRepository.getAllBusinessStream()
    }

    fun insertBusiness(business: Business) {
        viewModelScope.launch {
            businessRepository.insertBusiness(business)
        }
    }

    fun deleteBusiness(business: Business) {
        viewModelScope.launch {
            businessRepository.deleteBusiness(business)
        }
    }

    fun getAllMarket(): Flow<List<Market>> {
        return marketRepository.getAllMarketsStream()
    }

    fun insertMarket(market: Market) {
        viewModelScope.launch {
            marketRepository.insertMarket(market)
        }
    }

    fun deleteMarket(market: Market) {
        viewModelScope.launch {
            marketRepository.deleteMarket(market)
        }
    }

    fun getAllIncome(): Flow<List<Income>> {
        return incomeRepository.getAllIncomesStream()
    }

    fun insertIncome(income: Income) {
        viewModelScope.launch {
            incomeRepository.insertIncome(income)
        }
    }

    fun deleteIncome(income: Income) {
        viewModelScope.launch {
            incomeRepository.deleteIncome(income)
        }
    }

    fun getAllCompetitor(): Flow<List<Competitors>> {
        return competitorsRepository.getAllCompetitorsStream()
    }

    fun insertCompetitor(competitors: Competitors) {
        viewModelScope.launch {
            competitorsRepository.insertCompetitors(competitors)
        }
    }

    fun deleteCompetitor(competitors: Competitors) {
        viewModelScope.launch {
            competitorsRepository.deleteCompetitors(competitors)
        }
    }

    fun getAllStakeholders(): Flow<List<Stakeholders>> {
        return stakeholdersRepository.getAllStakeholdersStream()
    }

    fun insertStakeholders(stakeholders: Stakeholders) {
        viewModelScope.launch {
            stakeholdersRepository.insertStakeholders(stakeholders)
        }
    }

    fun deleteStakeholders(stakeholders: Stakeholders) {
        viewModelScope.launch {
            stakeholdersRepository.deleteStakeholders(stakeholders)
        }
    }

}