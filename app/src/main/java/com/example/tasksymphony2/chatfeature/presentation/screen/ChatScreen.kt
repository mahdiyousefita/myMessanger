package com.example.tasksymphony2.chatfeature.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tasksymphony2.R
import com.example.tasksymphony2.chatfeature.domain.model.Message
import com.example.tasksymphony2.chatfeature.presentation.component.ChatTextFieldDesk
import com.example.tasksymphony2.chatfeature.presentation.component.ChatTopBar
import com.example.tasksymphony2.chatfeature.presentation.viewmodel.ChatViewmodel
import com.example.tasksymphony2.ui.theme.Gray
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ChatScreen(navController: NavController, email: String){
    val viewModel: ChatViewmodel = hiltViewModel()
    LaunchedEffect(key1 = true) {
        viewModel.listenForMessages(email)
    }

    val messages = viewModel.messages.collectAsState()
    ChatMessages(
        messages = messages.value,
        onSendMessage = { message ->
            viewModel.SendMessage(email, message)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatMessages(
    messages: List<Message>,
    onSendMessage: (String) -> Unit,
){

    val msg = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            ChatTopBar(
                title = "test",
                titleIcon = 'A',
                backButton = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                videoCallIcon = painterResource(id = R.drawable.baseline_videocam_24)
            )
        }
    ) {
        ConstraintLayout(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(Color.Black)
        ) {
            val (
                chatLazy,
                bottomChatDesk
            ) = createRefs()


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bottomChatDesk) {
                        bottom.linkTo(parent.bottom, margin = 14.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                        end.linkTo(parent.end, margin = 12.dp)
                    }

            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Card(
                        modifier = Modifier
                            .size(34.dp)
                            .weight(0.1f),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E20)
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    onSendMessage(msg.value)
                                    msg.value = ""
                                }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_add_24),
                                contentDescription = null,
                                tint = Gray,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(12.dp))

                    OutlinedTextField(
                        shape = CircleShape,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Black
                        ),
                        value = msg.value,
                        onValueChange = {
                            msg.value = it
                        },
                        modifier = Modifier
                            .weight(0.9f)
                            .border(0.3f.dp, Gray, CircleShape),
                        placeholder = { Text(text = "MyMessage") },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    )

                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 40.dp)
                    .constrainAs(chatLazy) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(bottomChatDesk.top)
                    }
            ) {
                LazyColumn {
                    items(messages) { messages ->
                        ChatBubble(message = messages)
                    }
                }
            }

        }
    }
}

@Composable
fun ChatBubble(message: Message){
    val isCurrentUser = message.senderId == Firebase.auth.currentUser?.uid
    val bubbleColor = if (isCurrentUser){
        Color.Blue
    } else {
        Color.Red
    }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
    ){
        val alignment = if (!isCurrentUser) Alignment.CenterStart else Alignment.CenterEnd
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(bubbleColor, shape = RoundedCornerShape(8.dp))
                .align(alignment)
        ){
            Text(
                text = message.message,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}