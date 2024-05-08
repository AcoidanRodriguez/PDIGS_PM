package com.example.mybooks.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.example.mybooks.MainViewModel
import com.example.mybooks.R
import com.example.mybooks.data.Hr

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
