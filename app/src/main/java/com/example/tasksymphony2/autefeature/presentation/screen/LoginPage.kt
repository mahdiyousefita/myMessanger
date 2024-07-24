package com.example.tasksymphony2.autefeature.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tasksymphony2.R
import com.example.tasksymphony2.autefeature.presentation.component.Visibility
import com.example.tasksymphony2.autefeature.presentation.component.VisibilityOff
import com.example.tasksymphony2.autefeature.presentation.viewmodel.LoginViewModel
import com.example.tasksymphony2.autefeature.presentation.viewmodel.SinInState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {
    val viewModel : LoginViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()

    val contect = LocalContext.current

    LaunchedEffect(key1 = uiState.value) {
        when(uiState.value){
            is SinInState.Success -> {
                navController.navigate("home_screen")
            }
            is SinInState.Error -> {
                Toast.makeText(contect, "not ok", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {


        Box(
            modifier = Modifier
                /*.background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                )*/
                .align(Alignment.BottomCenter),
        ) {

            Image(
                painter = painterResource(id = R.drawable.user_sign_in),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),

                )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                ,

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //.........................Spacer
                Spacer(modifier = Modifier.height(50.dp))

                //.........................Text: title
                Text(
                    text = "Sign In",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 130.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(8.dp))
                val keyboardController = LocalSoftwareKeyboardController.current
                var text by rememberSaveable { mutableStateOf("") }

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    shape = RoundedCornerShape(topEnd =12.dp, bottomStart =12.dp),
                    label = {
                        Text("Name or Email Address",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium,
                        ) },
                    placeholder = { Text(text = "Name or Email Address") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            // do something here
                        }
                    )

                )

                Spacer(modifier = Modifier.padding(3.dp))
                val keyboardControllerPass = LocalSoftwareKeyboardController.current
                var password by rememberSaveable { mutableStateOf("") }
                var passwordHidden by rememberSaveable { mutableStateOf(true) }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    shape = RoundedCornerShape(topEnd =12.dp, bottomStart =12.dp),
                    label = {
                        Text("Enter Password",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium,
                        ) },
                    visualTransformation =
                    if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary),
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon =
                                if (passwordHidden) Visibility else VisibilityOff
                            // Please provide localized description for accessibility services
                            val description = if (passwordHidden) "Show password" else "Hide password"
                            Icon(imageVector = visibilityIcon, contentDescription = description)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardControllerPass?.hide()
                            // do something here
                        }
                    )
                )

                val gradientColor = listOf(Color(0xFF484BF1), Color(0xFF673AB7))
                val cornerRadius = 16.dp


                Spacer(modifier = Modifier.padding(10.dp))
                /* Button(
                     onClick = {},
                     modifier = Modifier
                         .fillMaxWidth(0.8f)
                         .height(50.dp)
                 ) {
                     Text(text = "Login", fontSize = 20.sp)
                 }*/

                val roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp)
                if (uiState.value == SinInState.Loading){
                    CircularProgressIndicator()
                } else {
                    androidx.compose.material3.Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, end = 32.dp),
                        enabled = text.isNotEmpty() && password.isNotEmpty() && (uiState.value == SinInState.Nothing || uiState.value == SinInState.Error),
                        onClick = {
                            viewModel.signIn(text, password)
                        },

                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(cornerRadius)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.horizontalGradient(colors = gradientColor),
                                    shape = roundedCornerShape
                                )
                                .clip(roundedCornerShape)
                                /*.background(
                                brush = Brush.linearGradient(colors = gradientColors),
                                shape = RoundedCornerShape(cornerRadius)
                            )*/
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Login",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    }
                }
//                GradientButton(
//                    gradientColors = gradientColor,
//                    cornerRadius = cornerRadius,
//                    nameButton = "Login",
//                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp)
//                )

                Spacer(modifier = Modifier.padding(10.dp))
                TextButton(onClick = {

                    navController.navigate("register_page"){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }

                }) {
                    Text(
                        text = "Create An Account",
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.labelLarge
                    )
                }


//                Spacer(modifier = Modifier.padding(5.dp))
//                TextButton(onClick = {
//
//                    navController.navigate("reset_page"){
//                        popUpTo(navController.graph.startDestinationId)
//                        launchSingleTop = true
//                    }
//
//                }) {
//                    Text(
//                        text = "Reset Password",
//                        letterSpacing = 1.sp,
//                        style = MaterialTheme.typography.labelLarge,
//                    )
//                }
                Spacer(modifier = Modifier.padding(20.dp))

            }


        }

    }


}


//...........................................................................

