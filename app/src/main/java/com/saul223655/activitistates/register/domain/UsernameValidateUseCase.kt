package com.saul223655.activitistates.register.domain

import com.saul223655.activitistates.register.data.model.UsernameValidateDTO
import com.saul223655.activitistates.register.data.repository.RegisterRepository

class UsernameValidateUseCase {
    private  val repository = RegisterRepository()

    suspend operator fun invoke() : Result<UsernameValidateDTO> {
        val result  = repository.validateUsername()
        println(result)
        // En caso de existir acá debe estar la lógica de negocio
        return result
    }
}