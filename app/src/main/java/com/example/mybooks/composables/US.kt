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
import androidx.navigation.NavHostController
import com.example.mybooks.MainViewModel
import com.example.mybooks.R
import com.example.mybooks.data.ProjectsRepository
import kotlinx.coroutines.launch

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