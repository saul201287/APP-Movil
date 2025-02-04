package com.saul223655.activitistates.menu.data.datasource

import com.saul223655.activitistates.menu.data.model.ProductCreateRequest
import com.saul223655.activitistates.menu.data.model.ProductResponseDTO
import com.saul223655.activitistates.menu.data.model.ProductValidateDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductServices {
    @GET("product")
    suspend fun getAllProducts(): Response<ProductResponseDTO>

    @POST("product")
    suspend fun createProduct(@Body request: ProductCreateRequest):Response<ProductValidateDTO>
}