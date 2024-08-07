package com.example.tasksymphony2.mainscreanfeature.presentation.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tasksymphony2.R
import com.example.tasksymphony2.mainscreanfeature.presentation.component.HomeScreenItems
import com.example.tasksymphony2.mainscreanfeature.presentation.viewmodel.HomeScreenViewmodel
import com.example.tasksymphony2.ui.theme.Blue
import com.google.android.play.integrity.internal.i

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeScreenViewmodel = hiltViewModel()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Messages"
                    )
                },
                actions = {
                    Row(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                            contentDescription = null,
                            tint = Blue,
                            modifier = Modifier
                                .clickable {
                                    Log.i(
                                        "??homeScreen??",
                                        "HomeScreen: " + "Icon show more clicked"
                                    )
                                }
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.baseline_chat_bubble_outline_24),
                            contentDescription = null,
                            tint = Blue,
                            modifier = Modifier
                                .clickable {
                                }
                        )
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn {
                item {
                    for (i in viewModel.users.keys) {
                        HomeScreenItems(name = viewModel.users[i].toString()){
                            navController.navigate("chat_screen/${viewModel.users[i]}")
                        }
                    }
                    Log.i("?????", "HomeScreen: " + viewModel.users.values.size)
//                    HomeScreenItems(name = viewModel.users.getOrDefault("mahdi2@gmail.com", "unknown").toString())
                }
            }
        }
    }
}