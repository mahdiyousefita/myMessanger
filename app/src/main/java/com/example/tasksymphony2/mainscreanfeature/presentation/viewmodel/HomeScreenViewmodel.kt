package com.example.tasksymphony2.mainscreanfeature.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.concurrent.Task
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewmodel @Inject constructor() : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    var users = mutableStateMapOf<String?, String?>()
    var res = mutableStateMapOf<String?, String?>()

    private var database: DatabaseReference = Firebase.database.reference
// ...


    init {
        //addUserToDB()
        //getUsers()
        writeNewUser(firebaseAuth.currentUser!!.uid, firebaseAuth.currentUser!!.displayName!!, firebaseAuth.currentUser!!.email!!)
        getAllUsers()
        //getUser()
    }

    private fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(name, email)

        Log.i("?????!!", "writeNewUser: f")
        database.child("users").child(userId).setValue(user).exception.let {
            Log.i("?????!!", "writeNewUser: $res")

        }
    }


    private fun getAllUsers(){
        database.child("users").get().addOnSuccessListener {
            Log.i("?????!!", "get values ${it.value})")
            val res = getUsersList(it.value.toString())
            res.forEach { user ->
                users[user.email] = user.username
            }
            Log.i("?????!!", "getAllUsers: $users")
        }.addOnFailureListener{
            Log.i("?????!!", "getAllUsers: ", it)
        }
    }



    private fun getUsersList(responseJson: String): MutableList<User> {

        // Parse the JSON into a map
        val responseMap: Map<String, Map<String, String>> = Gson().fromJson(responseJson, object : TypeToken<Map<String, Map<String, String>>>() {}.type)

// Create a list to hold User objects
        val userList = mutableListOf<User>()

// Iterate through the response map
        for ((userId, userProperties) in responseMap) {
            val username = userProperties["username"]
            val email = userProperties["email"]
            val user = User(username, email)
            userList.add(user)
        }
        return userList
    }


}

@IgnoreExtraProperties
data class User(val username: String? = null, val email: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}