package com.example.tasksymphony2.autefeature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<SinInState>(SinInState.Nothing)
    val state = _state.asStateFlow()

    fun signIn(email: String, password: String){
        _state.value = SinInState.Loading
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    task.result.user?.let {
                        _state.value = SinInState.Success
                        return@addOnCompleteListener
                    }
                    _state.value = SinInState.Error
                } else {
                    _state.value = SinInState.Error
                }
            }
    }
}
sealed class SinInState {
    object Nothing: SinInState()
    object Loading: SinInState()
    object Success: SinInState()
    object Error: SinInState()


}