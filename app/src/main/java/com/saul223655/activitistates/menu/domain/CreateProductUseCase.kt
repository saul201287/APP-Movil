package com.saul223655.activitistates.menu.domain

import com.saul223655.activitistates.menu.data.datasource.ProductServices
import com.saul223655.activitistates.menu.data.model.ProductCreateRequest
import com.saul223655.activitistates.menu.data.model.ProductValidateDTO
import com.saul223655.activitistates.menu.data.repository.ProductRepository

class CreateProductUseCase {
    private  val repository = ProductRepository()
    suspend operator fun invoke(request: ProductCreateRequest): Result<ProductValidateDTO>{
        val result = repository.createProduct(request)

        return  result
    }
}