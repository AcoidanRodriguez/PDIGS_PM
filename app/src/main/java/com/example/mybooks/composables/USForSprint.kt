package com.example.mybooks.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.mybooks.MainViewModel
import com.example.mybooks.R
import com.example.mybooks.data.ProjectsRepository
import kotlinx.coroutines.launch

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