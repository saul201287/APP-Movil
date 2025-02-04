package com.saul223655.activitistates.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saul223655.activitistates.register.data.model.CreateUserRequest
import com.saul223655.activitistates.register.domain.CreateUserUSeCase
import com.saul223655.activitistates.register.domain.UsernameValidateUseCase
import kotlinx.coroutines.launch

class RegisterViewModel() : ViewModel() {
    private val usernameUseCase = UsernameValidateUseCase()
    private val createUseCase = CreateUserUSeCase()

    private var _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private var _lastname = MutableLiveData<String>()
    val lastname : LiveData<String> = _lastname

    private var _username = MutableLiveData<String>()
    val username : LiveData<String> = _username

    private var _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private var _success = MutableLiveData<Boolean>(false)
    val success : LiveData<Boolean> = _success

    private var _error = MutableLiveData<String>("")
    val error : LiveData<String> = _error

    fun onChangeName (name : String) {
        _name.value = name
    }

    fun onChangeLastName (lastname : String) {
        _lastname.value = lastname
    }

    fun onChangeUsername(username : String) {
        _username.value = username
    }

    fun onChangePassword (password : String) {
        _password.value = password
    }

    suspend fun onFocusChanged() {
        viewModelScope.launch {
            val result = usernameUseCase()
            result.onSuccess {
                    data -> (
                    if (data.success) {
                        _success.value = data.success
                        _error.value = ""
                    }
                    else
                        _error.value = "El username ya exite"
                    )
            }.onFailure {
                    exception -> _error.value = exception.message ?: "Error desconocido"
            }
        }
    }

    suspend fun onClick(user : CreateUserRequest) {
        viewModelScope.launch {
            val result = createUseCase(user)
            result.onSuccess {
                    data -> (
                    if (data.success) {
                        _success.value = data.success
                        _error.value = ""

                    }
                    else
                        _error.value = "Error al registrarse"
                    )
                println(data)
            }.onFailure {
                    exception -> _error.value = exception.message ?: "Error desconocido"
            }
        }
    }
}