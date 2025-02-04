package com.saul223655.activitistates.menu.domain

import com.saul223655.activitistates.menu.data.model.ProductDTO
import com.saul223655.activitistates.menu.data.model.ProductResponseDTO
import com.saul223655.activitistates.menu.data.repository.ProductRepository

class GetAllProductsUseCase {
    private  val repository = ProductRepository()

    suspend operator fun invoke(): Result<ProductResponseDTO>{
        val result = repository.getAllProducts()

        return  result
    }

}