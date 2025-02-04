package com.saul223655.activitistates.menu.data.repository

import com.saul223655.activitistates.core.network.RetrofitHelper
import com.saul223655.activitistates.menu.data.model.ProductCreateRequest
import com.saul223655.activitistates.menu.data.model.ProductDTO
import com.saul223655.activitistates.menu.data.model.ProductResponseDTO
import com.saul223655.activitistates.menu.data.model.ProductValidateDTO

class ProductRepository()  {
    private val productServices = RetrofitHelper.productServices

    suspend fun createProduct(request: ProductCreateRequest) : Result<ProductValidateDTO>  {
        return try {

            val response = productServices.createProduct(request)
            println(response)
            println(response.body())
            if (response.isSuccessful) {
                Result.success(ProductValidateDTO(success=true))
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllProducts() : Result<ProductResponseDTO> {
        return try {
            val response = productServices.getAllProducts()
            println("papu" + response.body()?.data)
            println("cuerpo" + response.body())
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}