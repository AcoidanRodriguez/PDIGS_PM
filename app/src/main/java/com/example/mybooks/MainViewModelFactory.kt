package com.example.mybooks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
import com.example.mybooks.data.BusinessRepository
import com.example.mybooks.data.MarketRepository
import com.example.mybooks.data.IncomeRepository
import com.example.mybooks.data.Competitors
import com.example.mybooks.data.CompetitorsRepository
import com.example.mybooks.data.StakeholdersRepository


class MainViewModelFactory(
    private val usersRepository: UsersRepository,
    private val projectsRepository: ProjectsRepository,
    private val strengthsRepository: StrengthsRepository,
    private val weaknessesRepository: WeaknessesRepository,
    private val opportunitiesRepository: OpportunitiesRepository,
    private val threatsRepository: ThreatsRepository,
    private val hrsRepository: HrRepository,
    private val mrsRepository: MrRepository,
    private val frsRepository: FrRepository,
    private val irsRepository: IrRepository,
    private val businessRepository: BusinessRepository,
    private val marketRepository: MarketRepository,
    private val incomeRepository: IncomeRepository,
    private val competitorsRepository: CompetitorsRepository,
    private val stakeholdersRepository: StakeholdersRepository

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")

            return MainViewModel(usersRepository, projectsRepository, strengthsRepository, weaknessesRepository, opportunitiesRepository, threatsRepository, hrsRepository, mrsRepository, frsRepository, irsRepository, businessRepository, marketRepository, competitorsRepository, incomeRepository, stakeholdersRepository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
