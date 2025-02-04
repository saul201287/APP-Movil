package com.saul223655.activitistates.login.data.repository

import com.saul223655.activitistates.core.network.RetrofitHelper
import com.saul223655.activitistates.login.data.model.LoginUserRequest
import com.saul223655.activitistates.register.data.model.UsernameValidateDTO

class LoginRepository()  {
    private val loginService = RetrofitHelper.loginService

    suspend fun validateUsername(request: LoginUserRequest): Result<UsernameValidateDTO> {
        return try {
            val response = loginService.loginUser(request)
            println("Response: $response")

            if (response.isSuccessful) {
                println("Login exitoso (HTTP 200)")
                Result.success(UsernameValidateDTO(success = true))
            } else {
                println("Login fallido: HTTP ${response.code()}")
                Result.success(UsernameValidateDTO(success = false))
            }
        } catch (e: Exception) {
            println("Excepción: ${e.message}")
            Result.failure(e)
        }
    }

}