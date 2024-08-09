package com.example.tasksymphony2.chatfeature.domain.model

import com.google.firebase.firestore.FieldValue
import com.google.firebase.installations.time.SystemClock

data class Message(
    val id: String = "",
    val senderId: String = "",
    val message: String = "",
    val createdAt: Long = 0L,
    val senderName: String = "",
    val senderImage: String? = null,
    val imageUrl: String? = null
)
