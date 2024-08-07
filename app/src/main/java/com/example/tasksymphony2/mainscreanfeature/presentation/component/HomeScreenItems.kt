package com.example.tasksymphony2.mainscreanfeature.presentation.component

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tasksymphony2.mainscreanfeature.presentation.viewmodel.HomeScreenViewmodel

@Composable
fun HomeScreenItems(
    name: String,
    onClick: () -> Unit
){
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        val (
            cardIcon,
            textName
        ) = createRefs()

        Card(
            modifier = Modifier
                .size(53.dp)
                .constrainAs(cardIcon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF878A96)
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    color = Color.White,
                    fontSize = 25.sp,
                    modifier = Modifier.align(
                        Alignment.Center
                    ),
                    fontWeight = FontWeight.Bold,
                    text = name.first().toString().substring(0)
                )
            }
        }

        Text(
            fontWeight = FontWeight.Bold,
            text = name,
            modifier = Modifier
                .constrainAs(textName) {
                    top.linkTo(cardIcon.top)
                    bottom.linkTo(cardIcon.top)
                    start.linkTo(cardIcon.end, margin = 8.dp)
                }
        )

    }
}