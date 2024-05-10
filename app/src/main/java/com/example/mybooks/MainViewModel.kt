package com.example.mybooks


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.mybooks.data.User
import com.example.mybooks.data.Project
import com.example.mybooks.data.Strength
import com.example.mybooks.data.Weakness
import com.example.mybooks.data.Opportunity
import com.example.mybooks.data.Threat
import com.example.mybooks.data.Hr
import com.example.mybooks.data.Mr
import com.example.mybooks.data.Fr
import com.example.mybooks.data.Ir


import com.example.mybooks.data.UsersRepository
import com.example.mybooks.data.ProjectsRepository
import com.example.mybooks.data.StrengthsRepository
import com.example.mybooks.data.WeaknessesRepository
import com.example.mybooks.data.OpportunitiesRepository
import com.example.mybooks.data.ThreatsRepository
import com.example.mybooks.data.HrRepository
import com.example.mybooks.data.MrRepository
import com.example.mybooks.data.FrRepository
import com.example.mybooks.data.IrRepository

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


class MainViewModel(private val usersRepository: UsersRepository, private val projectsRepository: ProjectsRepository, private val strengthsRepository: StrengthsRepository, private val weaknessesRepository: WeaknessesRepository, private val opportunitiesRepository: OpportunitiesRepository, private val threatsRepository: ThreatsRepository, private val hrsRepository: HrRepository, private val mrsRepository: MrRepository, private val frsRepository: FrRepository, private val irsRepository: IrRepository,   private val businessRepository:BusinessRepository,private val marketRepository:MarketRepository,private val competitorsRepository: CompetitorsRepository,private val incomeRepository: IncomeRepository ,private val stakeholdersRepository: StakeholdersRepository) : ViewModel() {


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



    fun getAllStrengths(): Flow<List<Strength>> {
        return strengthsRepository.getAllStrengthsStream()
    }

    fun insertStrength(strength: Strength) {
        viewModelScope.launch {
            strengthsRepository.insertStrength(strength)
        }
    }

    fun deleteStrength(strength: Strength) {
        viewModelScope.launch {
            strengthsRepository.deleteStrength(strength)
        }
    }


    fun getAllWeaknesses(): Flow<List<Weakness>> {
        return weaknessesRepository.getAllWeaknessesStream()
    }

    fun insertWeakness(weakness: Weakness) {
        viewModelScope.launch {
            weaknessesRepository.insertWeakness(weakness)
        }
    }

    fun deleteWeakness(weakness: Weakness) {
        viewModelScope.launch {
            weaknessesRepository.deleteWeakness(weakness)
        }
    }


    fun getAllOpportunities(): Flow<List<Opportunity>> {
        return opportunitiesRepository.getAllOpportunitiesStream()
    }

    fun insertOpportunity(opportunity: Opportunity) {
        viewModelScope.launch {
            opportunitiesRepository.insertOpportunity(opportunity)
        }
    }

    fun deleteOpportunity(opportunity: Opportunity) {
        viewModelScope.launch {
            opportunitiesRepository.deleteOpportunity(opportunity)
        }
    }



    fun getAllThreats(): Flow<List<Threat>> {
        return threatsRepository.getAllThreatsStream()
    }

    fun insertThreat(threat: Threat) {
        viewModelScope.launch {
            threatsRepository.insertThreat(threat)
        }
    }

    fun deleteThreat(threat: Threat) {
        viewModelScope.launch {
            threatsRepository.deleteThreat(threat)
        }
    }

    fun getAllHrs(): Flow<List<Hr>> {
        return hrsRepository.getAllHrsStream()
    }

    fun insertHr(hr: Hr) {
        viewModelScope.launch {
            hrsRepository.insertHr(hr)
        }
    }

    fun deleteHr(hr: Hr) {
        viewModelScope.launch {
            hrsRepository.deleteHr(hr)
        }
    }


    fun getAllMrs(): Flow<List<Mr>> {
        return mrsRepository.getAllMrsStream()
    }

    fun insertMr(mr: Mr) {
        viewModelScope.launch {
            mrsRepository.insertMr(mr)
        }
    }

    fun deleteMr(mr: Mr) {
        viewModelScope.launch {
            mrsRepository.deleteMr(mr)
        }
    }


    fun getAllFrs(): Flow<List<Fr>> {
        return frsRepository.getAllFrsStream()
    }

    fun insertFr(fr: Fr) {
        viewModelScope.launch {
            frsRepository.insertFr(fr)
        }
    }

    fun deleteFr(fr: Fr) {
        viewModelScope.launch {
            frsRepository.deleteFr(fr)
        }
    }

    fun getAllIrs(): Flow<List<Ir>> {
        return irsRepository.getAllIrsStream()
    }

    fun insertIr(ir: Ir) {
        viewModelScope.launch {
            irsRepository.insertIr(ir)
        }
    }

    fun deleteIr(ir: Ir) {
        viewModelScope.launch {
            irsRepository.deleteIr(ir)
        }
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