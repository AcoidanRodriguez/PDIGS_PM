package com.example.mybooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mybooks.ui.theme.MyBooksTheme
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import com.example.mybooks.data.AppDataContainer
import com.example.mybooks.data.UsersRepository
import com.example.mybooks.data.ProjectsRepository

import com.example.mybooks.data.Project
import com.example.mybooks.data.Strength
import com.example.mybooks.data.Weakness
import com.example.mybooks.data.Opportunity
import com.example.mybooks.data.Threat
import com.example.mybooks.data.Hr
import com.example.mybooks.data.Mr
import com.example.mybooks.data.Fr
import com.example.mybooks.data.Ir

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight




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
                        composable("projectMain") {
                            ProjectMain(navController)
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







@Composable
fun Home(navController: NavHostController) {
    val orange = Color(0xFFE77A1C)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.logo_pm),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.8F,
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Log In")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Sign In")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Carlos Rodríguez del Toro",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Álvaro Cabrera Winter",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Acoidán Rodríguez Peña",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        )
    }
}





@Composable
fun Login(navController: NavHostController, usersRepository: UsersRepository) {
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_pm),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            var username by remember { mutableStateOf("") }
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )


            Spacer(modifier = Modifier.height(8.dp))

            var password by remember { mutableStateOf("") }
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            suspend fun loginUser(usern: String, passw: String) {
                val user = usersRepository.getUserStream(usern)
                user.collect { user ->
                    if (user != null) {
                        val username = user.username
                        val password = user.password
                        if (usern == username && passw == password) {
                            //viewModel.activateUser(user)
                            navController.navigate("myprojects")
                        }
                    }
                }
            }

            Button(
                onClick = {
                    val scope = CoroutineScope(Dispatchers.Main)
                    if (username.isEmpty() || password.isEmpty()) {
                        //navController.navigate("login")
                    } else {
                        scope.launch {
                            loginUser(username, password)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Log In")
            }
        }
    }
}


@Composable
fun Signin(navController: NavHostController, usersRepository: UsersRepository, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_pm),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val context = LocalContext.current

            val totalUsers = usersRepository.getTotalUsers()

            suspend fun registerUser() {
                var cont = 0
                totalUsers.collect {
                    if (cont < 1) {
                        cont = 1
                        viewModel.insertUser(it+1, username, password, email)
                    } else {
                        navController.navigate("login")
                    }
                }
            }

            Button(
                onClick = {
                    val scope = CoroutineScope(Dispatchers.Main)
                    if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        //navController.navigate("register")
                    } else if (password != confirmPassword) {
                        //navController.navigate("register")
                    } else {
                        scope.launch {
                            registerUser()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Sign In")
            }
        }
    }
}

@Composable
fun MyProjects(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val projects by viewModel.getAllProjects().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_pm),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addProject")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Create New Project")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("home")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Logout")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (projects.isEmpty()) {
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(projects) { project ->
                        Project(
                            navController,
                            project = project,
                            onClickDelete = {
                                viewModel.deleteProject(project)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}



@Composable
private fun Project(navController: NavHostController, project: Project, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${project.title}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Text(text = "${project.start_date} - ${project.end_date}")

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.navigate("projectMain")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Access", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Project", color = Color.White)
                }
            }
        }
    }
}



@Composable
fun addProject(navController: NavHostController, viewModel: MainViewModel) {
    var title by remember { mutableStateOf("") }
    var start_date by remember { mutableStateOf("") }
    var end_date by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.addbook),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Project Name:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Start Date:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = start_date,
                        onValueChange = { start_date = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "End Date:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = end_date,
                        onValueChange = { end_date = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (title.isNotEmpty()) {
                                val newProject = Project(
                                    username = "nombre_usuario",
                                    title = title,
                                    start_date = start_date,
                                    end_date = end_date,
                                )
                                viewModel.insertProject(newProject)
                                navController.navigate("myprojects")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Create Project", color = Color.White)
                    }
                }
            }
        }
    }
}



@Composable
fun ProjectMain(navController: NavHostController) {
    val orange = Color(0xFFE77A1C)
    var expandedES by remember { mutableStateOf(false) }
    var expandedSWOT by remember { mutableStateOf(false) }
    var expandedR by remember { mutableStateOf(false) }
    var expandedPB by remember { mutableStateOf(false) }
    var expandedSB by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            Image(
                painter = painterResource(R.drawable.logo_pm),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedES = !expandedES }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Executive Summary",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (expandedES) {
                        Button(
                            onClick = {
                                navController.navigate("projectMain")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Business Description", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("projectMain")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Market", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("projectMain")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Competitors", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("projectMain")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Income Sources", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("projectMain")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Stakeholders", color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedSWOT = !expandedSWOT }
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "SWOT",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (expandedSWOT) {
                        Button(
                            onClick = {
                                navController.navigate("strengths")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Strenghts", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("weaknesses")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Weaknesses", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("opportunities")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Opportunities", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("threats")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Threats", color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedR = !expandedR }
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Resources",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (expandedR) {
                        Button(
                            onClick = {
                                navController.navigate("hrs")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Human Resources", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("mrs")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Material Resources", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("frs")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Financial Resources", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("irs")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Informational Resources", color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedPB = !expandedPB }
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Product Backlog",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (expandedPB) {
                        Button(
                            onClick = {
                                navController.navigate("projectMain")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("User Stories", color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedSB = !expandedSB }
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Sprint Backlog",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp) 
                    )

                    if (expandedSB) {
                        Button(
                            onClick = {
                                navController.navigate("projectMain")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Story Point", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                navController.navigate("projectMain")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Sprints", color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("myprojects")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Close Project")
            }
        }
    }
}







/* ===================== STRENGTHS ============================== */

@Composable
fun Strengths(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val strengths by viewModel.getAllStrengths().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.strength),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addStrength")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add Strength")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (strengths.isEmpty()) {
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(strengths) { strength ->
                        Strength(
                            navController,
                            strength = strength,
                            onClickDelete = {
                                viewModel.deleteStrength(strength)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun Strength(navController: NavHostController, strength: Strength, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${strength.description}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Strength", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun addStrength(navController: NavHostController, viewModel: MainViewModel) {
    var description by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.strength),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Strength:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (description.isNotEmpty()) {
                                val newStrength = Strength(
                                    description = description,
                                )
                                viewModel.insertStrength(newStrength)
                                navController.navigate("strengths")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Add Strength", color = Color.White)
                    }
                }
            }
        }
    }
}














/* ===================== WEAKNESSES ============================== */

@Composable
fun Weaknesses(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val weaknesses by viewModel.getAllWeaknesses().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.weakness),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addWeakness")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add Weakness")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (weaknesses.isEmpty()) {
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(weaknesses) { weakness ->
                        Weakness(
                            navController,
                            weakness = weakness,
                            onClickDelete = {
                                viewModel.deleteWeakness(weakness)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun Weakness(navController: NavHostController, weakness: Weakness, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${weakness.description}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Weakness", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun addWeakness(navController: NavHostController, viewModel: MainViewModel) {
    var description by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.weakness),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Weakness:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (description.isNotEmpty()) {
                                val newWeakness = Weakness(
                                    description = description,
                                )
                                viewModel.insertWeakness(newWeakness)
                                navController.navigate("weaknesses")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Add Weakness", color = Color.White)
                    }
                }
            }
        }
    }
}














/* ===================== OPPORTUINITIES ============================== */

@Composable
fun Opportunities(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val opportunities by viewModel.getAllOpportunities().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.opportunity),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addOpportunity")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add Opportunity")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (opportunities.isEmpty()) {
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(opportunities) { opportunity ->
                        Opportunity(
                            navController,
                            opportunity = opportunity,
                            onClickDelete = {
                                viewModel.deleteOpportunity(opportunity)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun Opportunity(navController: NavHostController, opportunity: Opportunity, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${opportunity.description}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Opportunity", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun addOpportunity(navController: NavHostController, viewModel: MainViewModel) {
    var description by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.opportunity),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Opportunity:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (description.isNotEmpty()) {
                                val newOpportunity = Opportunity(
                                    description = description,
                                )
                                viewModel.insertOpportunity(newOpportunity)
                                navController.navigate("opportunities")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Add Opportunity", color = Color.White)
                    }
                }
            }
        }
    }
}














/* ===================== THREATS ============================== */

@Composable
fun Threats(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val threats by viewModel.getAllThreats().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.threat),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addThreat")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add Threat")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (threats.isEmpty()) {
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(threats) { threat ->
                        Threat(
                            navController,
                            threat = threat,
                            onClickDelete = {
                                viewModel.deleteThreat(threat)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun Threat(navController: NavHostController, threat: Threat, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${threat.description}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Threat", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun addThreat(navController: NavHostController, viewModel: MainViewModel) {
    var description by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.threat),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Threat:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (description.isNotEmpty()) {
                                val newThreat = Threat(
                                    description = description,
                                )
                                viewModel.insertThreat(newThreat)
                                navController.navigate("threats")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Add Threat", color = Color.White)
                    }
                }
            }
        }
    }
}






/* ===================== HUMAN RESOURCES ============================== */

@Composable
fun HRs(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val hrs by viewModel.getAllHrs().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.hr),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addHR")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add Human Resource")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (hrs.isEmpty()) {
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(hrs) { hr ->
                        HR(
                            navController,
                            hr = hr,
                            onClickDelete = {
                                viewModel.deleteHr(hr)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun HR(navController: NavHostController, hr: Hr, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${hr.name}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Age: ${hr.age}")
                Text(text = "Position: ${hr.position}")
                Text(text = "Salary: ${hr.salary}")
                Text(text = "Email: ${hr.email}")

                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Human Resource", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun addHR(navController: NavHostController, viewModel: MainViewModel) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.hr),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Name:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Age:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = age,
                        onValueChange = { age = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Position:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = position,
                        onValueChange = { position = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Salary:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = salary,
                        onValueChange = { salary = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Email:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (name.isNotEmpty()) {
                                val newHr = Hr(
                                    name = name,
                                    age = age,
                                    position = position,
                                    salary = salary,
                                    email = email
                                )
                                viewModel.insertHr(newHr)
                                navController.navigate("Hrs")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Add Human Resource", color = Color.White)
                    }
                }
            }
        }
    }
}






/* ===================== MATERIAL RESOURCES ============================== */

@Composable
fun MRs(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val mrs by viewModel.getAllMrs().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.mr),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addMR")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add Material Resource")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (mrs.isEmpty()) {
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(mrs) { mr ->
                        MR(
                            navController,
                            mr = mr,
                            onClickDelete = {
                                viewModel.deleteMr(mr)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun MR(navController: NavHostController, mr: Mr, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${mr.name}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Description: ${mr.description}")
                Text(text = "Quantity in Stock: ${mr.quantity}")
                Text(text = "Monetary Value: ${mr.value}")
                Text(text = "Supplier: ${mr.supplier}")
                Text(text = "Acquisition Date: ${mr.date}")

                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Material Resource", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun addMR(navController: NavHostController, viewModel: MainViewModel) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    var supplier by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.mr),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Resource Name:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Description:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Quantity in Stock:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = quantity,
                        onValueChange = { quantity = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Monetary Value:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = value,
                        onValueChange = { value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Supplier:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = supplier,
                        onValueChange = { supplier = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Acquisition Date:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = date,
                        onValueChange = { date = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (name.isNotEmpty()) {
                                val newMr = Mr(
                                    name = name,
                                    description = description,
                                    quantity = quantity,
                                    value = value,
                                    supplier = supplier,
                                    date = date
                                )
                                viewModel.insertMr(newMr)
                                navController.navigate("Mrs")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Add Material Resource", color = Color.White)
                    }
                }
            }
        }
    }
}




/* ===================== FINANCIAL RESOURCES ============================== */

@Composable
fun FRs(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val frs by viewModel.getAllFrs().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.fr),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addFR")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add Financial Resource")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (frs.isEmpty()) {
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(frs) { fr ->
                        FR(
                            navController,
                            fr = fr,
                            onClickDelete = {
                                viewModel.deleteFr(fr)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun FR(navController: NavHostController, fr: Fr, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${fr.name}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Type: ${fr.type}")
                Text(text = "Current Balance: ${fr.balance}")
                Text(text = "Interest Rate: ${fr.interest}")
                Text(text = "Financial Institution: ${fr.institution}")

                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Financial Resource", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun addFR(navController: NavHostController, viewModel: MainViewModel) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("") }
    var interest by remember { mutableStateOf("") }
    var institution by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.fr),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Resource Name:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Type:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = type,
                        onValueChange = { type = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Current Balance:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = balance,
                        onValueChange = { balance = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Interest Rate:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = interest,
                        onValueChange = { interest = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Financial Institution:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = institution,
                        onValueChange = { institution = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (name.isNotEmpty()) {
                                val newFr = Fr(
                                    name = name,
                                    type = type,
                                    balance = balance,
                                    interest = interest,
                                    institution = institution
                                )
                                viewModel.insertFr(newFr)
                                navController.navigate("Frs")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Add Financial Resource", color = Color.White)
                    }
                }
            }
        }
    }
}







/* ===================== INFORMATIONAL RESOURCES ============================== */

@Composable
fun IRs(navController: NavHostController, viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val irs by viewModel.getAllIrs().collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ir),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("addIR")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Add Informational Resource")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (irs.isEmpty()) {
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(irs) { ir ->
                        IR(
                            navController,
                            ir = ir,
                            onClickDelete = {
                                viewModel.deleteIr(ir)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun IR(navController: NavHostController, ir: Ir, onClickDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${ir.name}",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Type of Resource: ${ir.type}")
                Text(text = "Current Balance: ${ir.description}")
                Text(text = "Creation/Modification Date: ${ir.date}")

                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Delete Informational Resource", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun addIR(navController: NavHostController, viewModel: MainViewModel) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    val orange = Color(0xFFE77A1C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ir),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = orange)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Name:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Type of Informational Resource:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = type,
                        onValueChange = { type = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Description:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Date:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = date,
                        onValueChange = { date = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (name.isNotEmpty()) {
                                val newIr = Ir(
                                    name = name,
                                    type = type,
                                    description = description,
                                    date = date
                                )
                                viewModel.insertIr(newIr)
                                navController.navigate("Irs")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Add Informational Resource", color = Color.White)
                    }
                }
            }
        }
    }
}
