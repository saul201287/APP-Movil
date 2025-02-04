package com.saul223655.activitistates.core.network

import com.saul223655.activitistates.login.data.datasource.LoginService
import com.saul223655.activitistates.register.data.datasource.RegisterService
import com.saul223655.activitistates.menu.data.datasource.ProductServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://api-movil.piweb.lat/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val registerService: RegisterService by lazy {
        retrofit.create(RegisterService::class.java)
    }

    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }

    val productServices: ProductServices by lazy {
        retrofit.create(ProductServices::class.java)
    }
}