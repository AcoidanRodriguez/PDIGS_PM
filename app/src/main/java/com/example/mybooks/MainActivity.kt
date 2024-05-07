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
import androidx.compose.ui.text.style.TextAlign
import com.example.mybooks.data.AppDataContainer
import com.example.mybooks.data.UsersRepository
import com.example.mybooks.data.Project
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
import androidx.lifecycle.viewModelScope
import com.example.mybooks.data.ProjectsRepository


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels(){
        MainViewModelFactory(appContainer.usersRepository, appContainer.projectsRepository)
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
                        composable("userStoriesSprint/{projectId}"){
                            navBackStackEntry ->
                            val pr4 = navBackStackEntry.arguments.toString()
                            val projectIdRegex = Regex("projectId=(\\d+)")
                            val matchResult = projectIdRegex.find(pr4)
                            val projectId = matchResult?.groupValues?.getOrNull(1)?.toIntOrNull()
                            if(projectId != null){
                                UserStoriesSprint(projectId,appContainer.projectsRepository,viewModel)
                            }
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
                        navController.navigate("projectMain/${project.id}")
                         // ProjectMain(navController = navController , projectId = 3 )
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
fun ProjectMain(navController: NavHostController, projectId: Int) {
    val orange = Color(0xFFE77A1C)
    var expandedES by remember { mutableStateOf(false) }
    var expandedSWOT by remember { mutableStateOf(false) }
    var expandedR by remember { mutableStateOf(false) }
    var expandedPB by remember { mutableStateOf(false) }
    var expandedSB by remember { mutableStateOf(false) }
    var id by remember { mutableStateOf(-1)    }
    id = projectId


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
                                navController.navigate("projectMain")
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
                                navController.navigate("projectMain")
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
                                navController.navigate("projectMain")
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
                                navController.navigate("projectMain")
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
                                navController.navigate("projectMain")
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
                                navController.navigate("projectMain")
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
                                navController.navigate("projectMain")
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
                                navController.navigate("projectMain")
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
                                navController.navigate("userStories/$id")
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
                                navController.navigate("userStoriesSprint/$id")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Sprint US", color = Color.White)
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




@Composable
fun UserStories(
    navController: NavHostController,
    pr: ProjectsRepository,
    projectId: Int,
    viewModel: MainViewModel
){
    val orange = Color(0xFFE77A1C)
    var id by remember { mutableStateOf(-1) }
    id = projectId
    var newStory by remember { mutableStateOf("") }
    var userStoriesState by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(key1 = id) {
        pr.getUserStoriesForProjectID(id).collect { userStories ->
            userStoriesState = userStories
        }
    }

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
            Text("User Stories:")

            LazyColumn {
                //userStoriesState devolvia una lista con un solo eleemtno con todas las US, por eso es necesari esto
                val userStories = userStoriesState.firstOrNull()?.split(",")?: emptyList()
                var counter = 1
                items(userStories){ us ->
                    if(us.trim() != "") {
                        Text("US-$counter $us".trim())
                        counter++
                    }
                }
            }


            TextField(
                value = newStory,
                onValueChange = { newStory = it },
                label = { Text("New User Story") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Button(
                onClick = {
                    viewModel.viewModelScope.launch {
                        pr.addUserStoryToProject(id, newStory)
                        newStory = ""
                    }

                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text("Add User Story")
            }
        }
    }

}

@Composable
fun UserStoriesSprint(
    projectId: Int,
    pr: ProjectsRepository,
    viewModel: MainViewModel
){
    val orange = Color(0xFFE77A1C)
    var id = projectId
    Text(projectId.toString())
    var idNewStory by remember { mutableStateOf("") }
    var userStoriesState by remember { mutableStateOf<List<String>>(emptyList()) }
    var userStoriesForProject by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(key1 = id) {
        pr.getUserStoriesForProjectID(id).collect { userStories ->
            userStoriesState = userStories
        }
    }
    LaunchedEffect(key1 = id) {
        pr.getUserStoriesForSprintForProject(id).collect { userStories ->
            userStoriesForProject = userStories
        }
    }

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
            Text("User Stories:")

            LazyColumn {
                //userStoriesState devolvia una lista con un solo eleemtno con todas las US, por eso es necesari esto
                val userStories = userStoriesForProject.firstOrNull()?.split(",")?: emptyList()
                //Faltaria que el id de la historia coincida con el del PB y ya
                var counter = 1
                items(userStories){ us ->
                    Text(us)
                    counter++
                }
            }

            TextField(
                value = idNewStory,
                onValueChange = { idNewStory= it },
                label = { Text(text = "Insert user story number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )


            fun obtainUS(): String {
                val userStoriesList = userStoriesState.firstOrNull()?.split(",")?: emptyList()
                var counter = 0
                var idUS = idNewStory.toInt() //el usuario tiene que meter un numero obligatoriamente, y que no sea 0
                //idUS-- // para tener la posicion en la lista -> no hace falta por que la primera posicion parece ser que siempre la ocupa una string vacia.
                var newStory=""
                userStoriesList.forEach { us ->
                    if(idUS==counter){
                        newStory=us
                    }
                    counter++
                }
                return newStory
            }

            Button(
                onClick = {
                    viewModel.viewModelScope.launch {
                        pr.addUserStoryForSprintToProject(id, "US-$idNewStory ${obtainUS()}")
                    }
                    idNewStory=""
                },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text("Add User Story to sprint")
            }
        }
    }
}
















/*

@Composable
fun Perfil(navController: NavHostController, viewModel: MainViewModel) {
    var correo by remember { mutableStateOf("correo1@email.com") }
    var usuario by remember { mutableStateOf("prueba1") }
    var contraseña by remember { mutableStateOf("contraseña1") }
    val orange = Color(0xFFE77A1C)

    /*
    val activeUser = viewModel.getActiveUser()

    activeUser?.let {
        usuario = it.username
        contraseña = it.password
    }
    */

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = orange)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.8F,
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Correo electrónico:")
        TextField(
            value = correo,
            onValueChange = { correo = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Usuario:")
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Contraseña:")
        TextField(
            value = contraseña,
            onValueChange = { contraseña = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("main")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Guardar", color = Color.White)
        }

        Button(
            onClick = {
                //viewModel.deactivateUser(activeUser)
                navController.navigate("home")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Cerrar Sesión", color = Color.White)
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Principal(navController: NavHostController
) {
    val navyBlue = Color(0xFF001F3F)
    val orange = Color(0xFFE77A1C)
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

        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("biblioteca")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Biblioteca")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("futuraslecturas")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Futuras Lecturas")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("leidos")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Leídos")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("añadirlibro")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Añadir Libro")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("perfil")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Perfil")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("ayuda")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Ayuda")
            }
        }
    }
}

@Composable
fun Biblioteca(viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val libros by viewModel.getAllBooks().collectAsState(initial = emptyList())

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
                painter = painterResource(R.drawable.biblio),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (libros.isEmpty()) {
                Text("No hay libros en la Biblioteca", color = Color.White)
            } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(libros) { libro ->
                    BookItem(
                        libro = libro,
                        onClickDelete = {
                            viewModel.deleteBook(libro)
                        },
                        onAddToFavorites = {
                            viewModel.addToFavorites(libro)
                        },
                        onAddToRead = {
                            viewModel.addToRead(libro)
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
private fun BookItem(libro: Book, onClickDelete: () -> Unit, onAddToFavorites: () -> Unit, onAddToRead: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "${libro.name}")

            if (expanded) {
                Text(text = "Autor: ${libro.author}")
                Text(text = "Género: ${libro.genre}")
                Text(text = "Descripción: ${libro.description}")

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onAddToFavorites,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Añadir a Futuras Lecturas", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onAddToRead,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Añadir a Leídos", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onClickDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Eliminar de la Biblioteca", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun FuturasLecturas(viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)
    val libros by viewModel.getFavoriteBooks().collectAsState(initial = emptyList())

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
                painter = painterResource(R.drawable.future),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (libros.isEmpty()) {
                Text("No hay libros en Futuras Lecturas", color = Color.White)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(libros) { libro ->
                        BookItemFavorite(
                            libro = libro,
                            onDeleteFromFavorites = {
                                viewModel.deleteFromFavorites(libro)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun BookItemFavorite(libro: Book, onDeleteFromFavorites: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "${libro.name}")

            if (expanded) {
                Text(text = "Autor: ${libro.author}")
                Text(text = "Género: ${libro.genre}")
                Text(text = "Descripción: ${libro.description}")

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onDeleteFromFavorites,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Eliminar de Futuras Lecturas", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun Leidos(viewModel: MainViewModel) {
    val orange = Color(0xFFE77A1C)

    val libros by viewModel.getReadBooks().collectAsState(initial = emptyList())

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
                painter = painterResource(R.drawable.completed),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.8F,
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (libros.isEmpty()) {
                Text("No hay libros en Leídos", color = Color.White)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(libros) { libro ->
                        BookItemRead(
                            libro = libro,
                            onDeleteFromRead = {
                                viewModel.deleteFromRead(libro)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun BookItemRead(libro: Book, onDeleteFromRead: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "${libro.name}")

            if (expanded) {
                Text(text = "Autor: ${libro.author}")
                Text(text = "Género: ${libro.genre}")
                Text(text = "Descripción: ${libro.description}")

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onDeleteFromRead,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Eliminar de Leídos", color = Color.White)
                }
            }
        }
    }
}


@Composable
fun añadirLibro(navController: NavHostController, booksRepository: BooksRepository, viewModel: MainViewModel) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
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
                        text = "Título:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Autor:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = autor,
                        onValueChange = { autor = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Género:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = genero,
                        onValueChange = { genero = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Descripción:",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    )
                    TextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (titulo.isNotEmpty()) {
                                val nuevoLibro = Book(
                                    username = "nombre_usuario",
                                    name = titulo,
                                    author = autor,
                                    description = descripcion,
                                    genre = genero
                                )
                                viewModel.insertBook(nuevoLibro)
                                navController.navigate("main")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Añadir a la Biblioteca", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun Ayuda(){
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
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.question),
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
                        """
                USO:
                
                Biblioteca:
                Visualiza toda la colección de libros que has añadido. Aquí podrás añadir un libro a la sección de 'Futuras Lecturas' o 'Leídos' .

                Futuras Lecturas:
                Añade aquí desde la 'Biblioteca' todos los libros que te interesen y desees leer en un futuro.

                Leídos:
                Guarda aquí desde la 'Biblioteca' todos los libros que ya hayas leído y quieras recordar.

                Añadir Libro:
                Añade los libros que te interesen a tu 'Biblioteca'. El único campo obligatorio será el título. Eso sí, también puedes añadir el autor, el género y una descripción con los detalles que consideres importantes del libro.

                Perfil:
                Actualiza tu correo electrónico, nombre de usuario o contraseña para cada vez que inicies sesión. Además podrás la cerrar sesión de usuario actualmente abierta.
                
                
                
                CONTACTO:
                
                Email:
                carlos.rodriguez153@alu.ulpgc.es
                antonio.medina115@alu.ulpgc.es
            """.trimIndent(), color = Color.White
                    )
                }
            }
        }
    }
}
*/