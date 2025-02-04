package com.saul223655.activitistates.register.data.repository

import com.saul223655.activitistates.core.network.RetrofitHelper
import com.saul223655.activitistates.register.data.model.CreateUserRequest
import com.saul223655.activitistates.register.data.model.UsernameValidateDTO

class RegisterRepository()  {
    private val registerService = RetrofitHelper.registerService

    suspend fun validateUsername() : Result<UsernameValidateDTO>  {
        return try {

            val response = registerService.validateUsername()

            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createUser(request : CreateUserRequest) : Result<UsernameValidateDTO> {
        return try {
            val response = registerService.createUser(request)
            println(response)
            if (response.isSuccessful) {
                Result.success(UsernameValidateDTO(success = true))
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}