package com.example.tasksymphony2.chatfeature.presentation.component

import android.graphics.drawable.Icon
import android.icu.text.CaseMap.Title
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasksymphony2.ui.theme.Blue

@Composable
fun ChatTopBar(
    title: String,
    titleIcon: Char,
    backButton: Painter,
    videoCallIcon: Painter
    ){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().padding(top = 30.dp)
            .background(Color(0xFF202020))

    ) {
        Icon(
            painter = backButton,
            contentDescription = null,
            tint = Blue,
            modifier = Modifier.size(50.dp)
                .padding(12.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
        ) {
            Card(
                modifier = Modifier
                    .size(53.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF878A96)
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        color = androidx.compose.ui.graphics.Color.White,
                        fontSize = 25.sp,
                        modifier = Modifier.align(
                            Alignment.Center
                        ),
                        fontWeight = FontWeight.Bold,
                        text = titleIcon.toString()
                    )
                }
            }

            Text(
                text = title,
                fontSize = 12.sp
            )
        }

        Icon(
            painter = videoCallIcon,
            contentDescription = null,
            tint = Blue,
            modifier = Modifier.size(54.dp).padding(12.dp)
        )

    }

}