package com.example.mybooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mybooks.ui.theme.MyBooksTheme
import androidx.navigation.compose.rememberNavController
import com.example.mybooks.data.AppDataContainer
import androidx.compose.material3.MaterialTheme
import com.example.mybooks.composables.*

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels(){
        MainViewModelFactory(appContainer.usersRepository, appContainer.projectsRepository,appContainer.businessRepository,appContainer.marketRepository,appContainer.incomeRepository,appContainer.competitorsRepository,appContainer.stakeholdersRepository)
    }
    private lateinit var appContainer: AppDataContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppDataContainer(applicationContext)

        setContent {
            MyBooksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "home") {
                        composable("home") {
                            Home(navController)
                        }
                        composable("login") {
                            Login(navController, appContainer.usersRepository)
                        }
                        composable("register") {
                            Signin(navController, appContainer.usersRepository, viewModel)
                        }
                        composable("myprojects") {
                            MyProjects(navController, viewModel)
                        }
                        composable("addProject") {
                            addProject(navController, viewModel)
                        }
                        composable("projectMain") {
                            ProjectMain(navController)
                        }
                        composable("businesses") {
                            Businesses(navController,viewModel)
                        }
                        composable("addbusinesses") {
                            addBusiness(navController,viewModel)
                        }
                        composable("Markets") {
                            Markets(navController,viewModel)
                        }
                        composable("addmarket") {
                            addMarket(navController,viewModel)
                        }
                        composable("Competitors") {
                            Competitors(navController,viewModel)
                        }
                        composable("addcompetitor") {
                            addCompetitor(navController,viewModel)
                        }
                        composable("Incomes") {
                            Incomes(navController,viewModel)
                        }
                        composable("addincomes") {
                            addIncome(navController,viewModel)
                        }
                        composable("Stakeholders") {
                            Stakeholders(navController,viewModel)
                        }
                        composable("addstakeholders") {
                            addStakeholder(navController,viewModel)
                        }


                    }
                }
            }
        }
    }
}