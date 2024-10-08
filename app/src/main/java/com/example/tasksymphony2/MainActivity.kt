package com.example.tasksymphony2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasksymphony2.autefeature.presentation.screen.LoginPage
import com.example.tasksymphony2.autefeature.presentation.screen.RegisterPage
import com.example.tasksymphony2.autefeature.presentation.screen.ResetPage
import com.example.tasksymphony2.chatfeature.presentation.screen.ChatScreen
import com.example.tasksymphony2.mainscreanfeature.presentation.screen.HomeScreen
import com.example.tasksymphony2.ui.theme.TaskSymphony2Theme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskSymphony2Theme {
                LoginApplication()
            }
        }
    }
}


@Composable
fun LoginApplication(){

    val firestore = FirebaseFirestore.getInstance()
    firestore.firestoreSettings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(false) // Disable offline persistence
        .build()

    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val start = if(currentUser != null) "home_screen" else "login_page"
    NavHost(navController = navController, startDestination = start, builder = {
        composable("login_page", content = { LoginPage(navController = navController) })
        composable("register_page", content = { RegisterPage(navController = navController) })
        composable("reset_page", content = { ResetPage(navController = navController) })
        composable("home_screen", content = { HomeScreen(navController = navController) })
        composable("chat_screen/{email}", arguments = listOf(
            navArgument("email"){
                type = NavType.StringType
            }
        )){
            val email = it.arguments?.getString("email") ?: ""
            ChatScreen(navController = navController, email = email)
        }
    })
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskSymphony2Theme {
        Greeting("Android")
    }
}