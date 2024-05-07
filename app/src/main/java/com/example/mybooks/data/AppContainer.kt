package com.example.mybooks.data

import android.content.Context

interface AppContainer {
    val usersRepository: UsersRepository
    val projectsRepository: ProjectsRepository
    val businessRepository: BusinessRepository
    val marketRepository: MarketRepository
    val competitorsRepository: CompetitorsRepository
    val incomeRepository: IncomeRepository
    val stakeholdersRepository: StakeholdersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val usersRepository: UsersRepository by lazy {
        val database = UserDatabase.getDatabase(context)
        OfflineUsersRepository(database.userDao())
    }

    override val projectsRepository: ProjectsRepository by lazy {
        OfflineProjectsRepository(ProjectDatabase.getDatabase(context).projectDao())
    }
    override val businessRepository: BusinessRepository by lazy {
        OfflineBusinessesRepository(BusinessDatabase.getDatabase(context).BusinessDao())
    }

    override val marketRepository: MarketRepository by lazy {
        OfflineMarketsRepository(MarketDatabase.getDatabase(context).MarketDao())
    }

    override val competitorsRepository: CompetitorsRepository by lazy {
        OfflineCompetitorsRepository(CompetitorsDatabase.getDatabase(context).CompetitorsDao())
    }

    override val incomeRepository: IncomeRepository by lazy {
        OfflineIncomesRepository(IncomeDatabase.getDatabase(context).IncomeDao())
    }
    override val stakeholdersRepository: StakeholdersRepository by lazy {
        OfflineStakeholdersRepository(StakeholdersDatabase.getDatabase(context).StakeholdersDao())
    }

}