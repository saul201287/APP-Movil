package com.saul223655.activitistates.login.data.datasource

import com.saul223655.activitistates.login.data.model.LoginUserRequest
import com.saul223655.activitistates.register.data.model.UserDTO
import com.saul223655.activitistates.register.data.model.UsernameValidateDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {
    /*@GET("users/{username}")
    suspend fun validateUsername(@Path("username") username : String) : Response<UsernameValidateDTO>
    */
    @POST("user/login")
    suspend fun loginUser(@Body request : LoginUserRequest) : Response<UsernameValidateDTO>
}