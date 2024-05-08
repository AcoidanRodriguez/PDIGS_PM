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
        MainViewModelFactory(appContainer.usersRepository, appContainer.projectsRepository, appContainer.strengthsRepository, appContainer.weaknessesRepository, appContainer.opportunitiesRepository, appContainer.threatsRepository, appContainer.hrsRepository, appContainer.mrsRepository, appContainer.frsRepository, appContainer.irsRepository)
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
                        composable("projectMain/{projectId}") {
                            navBackStackEntry ->
                            //val projectId = navBackStackEntry.arguments?.getInt("projectId")
                            val pr2 = navBackStackEntry.arguments.toString()
                            val projectIdRegex = Regex("projectId=(\\d+)")
                            val matchResult = projectIdRegex.find(pr2)
                            val projectId = matchResult?.groupValues?.getOrNull(1)?.toIntOrNull()

                            if(projectId != null){
                                ProjectMain(navController,projectId)
                            }

                        }
                        composable("userStories/{projectId}"){
                            navBackStackEntry ->
                            val pr3 = navBackStackEntry.arguments.toString()
                            val projectIdRegex = Regex("projectId=(\\d+)")
                            val matchResult = projectIdRegex.find(pr3)
                            val projectId = matchResult?.groupValues?.getOrNull(1)?.toIntOrNull()
                            if(projectId != null){
                                UserStories(navController,appContainer.projectsRepository,projectId,viewModel)
                            }
                        }
                        composable("userStoriesSprint/{projectId}") { navBackStackEntry ->
                            val pr4 = navBackStackEntry.arguments.toString()
                            val projectIdRegex = Regex("projectId=(\\d+)")
                            val matchResult = projectIdRegex.find(pr4)
                            val projectId = matchResult?.groupValues?.getOrNull(1)?.toIntOrNull()
                            if (projectId != null) {
                                UserStoriesSprint(
                                    projectId,
                                    appContainer.projectsRepository,
                                    viewModel
                                )
                            }
                        }
                        composable("strengths") {
                            Strengths(navController, viewModel)
                        }
                        composable("addStrength") {
                            addStrength(navController, viewModel)
                        }
                        composable("weaknesses") {
                            Weaknesses(navController, viewModel)
                        }
                        composable("addWeakness") {
                            addWeakness(navController, viewModel)
                        }
                        composable("opportunities") {
                            Opportunities(navController, viewModel)
                        }
                        composable("addOpportunity") {
                            addOpportunity(navController, viewModel)
                        }
                        composable("threats") {
                            Threats(navController, viewModel)
                        }
                        composable("addThreat") {
                            addThreat(navController, viewModel)
                        }
                        composable("hrs") {
                            HRs(navController, viewModel)
                        }
                        composable("addHr") {
                            addHR(navController, viewModel)
                        }
                        composable("mrs") {
                            MRs(navController, viewModel)
                        }
                        composable("addMr") {
                            addMR(navController, viewModel)
                        }
                        composable("frs") {
                            FRs(navController, viewModel)
                        }
                        composable("addFr") {
                            addFR(navController, viewModel)
                        }
                        composable("irs") {
                            IRs(navController, viewModel)
                        }
                        composable("addIr") {
                            addIR(navController, viewModel)

                        }
                    }
                }
            }
        }
    }
}