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
    private val irsRepository: IrRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(usersRepository, projectsRepository, strengthsRepository, weaknessesRepository, opportunitiesRepository, threatsRepository, hrsRepository, mrsRepository, frsRepository, irsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
