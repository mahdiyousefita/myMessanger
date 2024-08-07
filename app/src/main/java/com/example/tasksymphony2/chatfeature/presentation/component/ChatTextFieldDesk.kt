package com.example.tasksymphony2.chatfeature.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasksymphony2.R
import com.example.tasksymphony2.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTextFieldDesk(
    onSendBtnClicked: () -> Unit,
    msg: MutableState<String> = remember {
        mutableStateOf("")
    }
){
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
                    .clickable { onSendBtnClicked() }
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
            value = "test",
            onValueChange = {

        },
            modifier = Modifier
                .weight(0.9f)
                .border(0.3f.dp, Gray, CircleShape)
        )

    }
}