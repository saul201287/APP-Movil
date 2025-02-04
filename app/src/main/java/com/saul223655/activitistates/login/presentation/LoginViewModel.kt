package com.saul223655.activitistates.login.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saul223655.activitistates.login.domain.LoginUserUseCase
import com.saul223655.activitistates.login.data.model.LoginUserRequest
import com.saul223655.activitistates.register.domain.UsernameValidateUseCase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var loginUserUseCase = LoginUserUseCase()
    private val usernameUseCase = UsernameValidateUseCase()

    private var _username = MutableLiveData<String>()
    val username : LiveData<String> = _username

    private var _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private var _success = MutableLiveData<Boolean>(false)
    val success : LiveData<Boolean> = _success

    private var _error = MutableLiveData<String>("")
    val error : LiveData<String> = _error

    fun onChangeUsername(username : String) {
        _username.value = username
    }

    fun onChangePassword (password : String) {
        _password.value = password
    }

    suspend fun onClick(user : LoginUserRequest) {
        viewModelScope.launch {
            val result = loginUserUseCase(user)
            result.onSuccess {
                    data -> (
                    if (data.success) {
                        _success.value = data.success
                        _error.value = ""

                    }
                    else
                        _error.value = "El username o/y password son incorrectos"
                    )
                println(data)
            }.onFailure {
                    exception -> _error.value = exception.message ?: "Error desconocido"
            }
        }
    }
}