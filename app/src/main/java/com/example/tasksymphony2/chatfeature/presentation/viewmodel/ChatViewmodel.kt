package com.example.tasksymphony2.chatfeature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tasksymphony2.chatfeature.domain.model.Message
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FieldValue
import com.google.firebase.installations.time.SystemClock
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class ChatViewmodel @Inject constructor() : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()
    private val db = Firebase.database

    fun SendMessage(email: String, messageText: String){
        Log.i("????!!!!", "SendMessage: ${FieldValue.serverTimestamp()}")
        val message = Message(
            db.reference.push().key ?: UUID.randomUUID().toString(),
            Firebase.auth.currentUser?.uid ?: "",
            messageText,
            0L,
            Firebase.auth.currentUser?.displayName ?: "",
            null,
            null
        )

//        db.reference.child("messages").child(email).push().setValue(message)

        val messageRef = db.reference.child("messages").child(email).push()
        messageRef.setValue(message).addOnSuccessListener {
            // Update the createdAt field with the server timestamp
            messageRef.child("createdAt").setValue(ServerValue.TIMESTAMP)
        }
    }

    fun listenForMessages(email: String) {
        db.getReference("messages").child(email).orderByChild("createdAt")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Message>()
                    snapshot.children.forEach { data ->
                        val message = data.getValue(Message::class.java)
                        message?.let {
                            list.add(it)
                        }
                    }
                    _messages.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }
}