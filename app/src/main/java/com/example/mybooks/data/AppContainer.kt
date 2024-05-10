package com.example.mybooks.data

import android.content.Context

interface AppContainer {
    val usersRepository: UsersRepository
    val projectsRepository: ProjectsRepository
    val strengthsRepository: StrengthsRepository
    val weaknessesRepository: WeaknessesRepository
    val opportunitiesRepository: OpportunitiesRepository
    val threatsRepository: ThreatsRepository
    val hrsRepository: HrRepository
    val mrsRepository: MrRepository
    val frsRepository: FrRepository
    val irsRepository: IrRepository
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

    override val strengthsRepository: StrengthsRepository by lazy {
        OfflineStrengthsRepository(StrengthDatabase.getDatabase(context).strengthDao())
    }

    override val weaknessesRepository: WeaknessesRepository by lazy {
        OfflineWeaknessesRepository(WeaknessDatabase.getDatabase(context).weaknessDao())
    }

    override val opportunitiesRepository: OpportunitiesRepository by lazy {
        OfflineOpportunitiesRepository(OpportunityDatabase.getDatabase(context).opportunityDao())
    }

    override val threatsRepository: ThreatsRepository by lazy {
        OfflineThreatsRepository(ThreatDatabase.getDatabase(context).threatDao())
    }

    override val hrsRepository: HrRepository by lazy {
        OfflineHrsRepository(HrDatabase.getDatabase(context).hrDao())
    }

    override val mrsRepository: MrRepository by lazy {
        OfflineMrsRepository(MrDatabase.getDatabase(context).mrDao())
    }

    override val frsRepository: FrRepository by lazy {
        OfflineFrsRepository(FrDatabase.getDatabase(context).frDao())
    }

    override val irsRepository: IrRepository by lazy {
        OfflineIrsRepository(IrDatabase.getDatabase(context).irDao())
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