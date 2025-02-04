package com.saul223655.activitistates.register.data.datasource

import com.saul223655.activitistates.register.data.model.CreateUserRequest
import com.saul223655.activitistates.register.data.model.UsernameValidateDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisterService {
    /*@GET("users/{username}")
    suspend fun validateUsername(@Path("username") username : String) : Response<UsernameValidateDTO>
    */
    @GET("user")
    suspend fun validateUsername() : Response<UsernameValidateDTO>

    @POST("user")
    suspend fun createUser(@Body request : CreateUserRequest) : Response<UsernameValidateDTO>
}