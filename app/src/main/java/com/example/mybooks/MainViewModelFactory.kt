package com.example.mybooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.data.UsersRepository
import com.example.mybooks.data.ProjectsRepository
import com.example.mybooks.data.BusinessRepository
import com.example.mybooks.data.MarketRepository
import com.example.mybooks.data.IncomeRepository
import com.example.mybooks.data.Competitors
import com.example.mybooks.data.CompetitorsRepository
import com.example.mybooks.data.StakeholdersRepository

class MainViewModelFactory(
    private val usersRepository: UsersRepository,
    private val projectsRepository: ProjectsRepository,
    private val businessRepository: BusinessRepository,
    private val marketRepository: MarketRepository,
    private val incomeRepository: IncomeRepository,
    private val competitorsRepository: CompetitorsRepository,
    private val stakeholdersRepository: StakeholdersRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(usersRepository, projectsRepository, businessRepository, marketRepository, competitorsRepository, incomeRepository, stakeholdersRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
