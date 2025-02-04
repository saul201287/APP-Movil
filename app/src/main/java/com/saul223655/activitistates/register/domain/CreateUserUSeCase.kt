package com.saul223655.activitistates.register.domain

import com.saul223655.activitistates.register.data.model.CreateUserRequest
import com.saul223655.activitistates.register.data.model.UsernameValidateDTO
import com.saul223655.activitistates.register.data.repository.RegisterRepository

class CreateUserUSeCase {
    private  val repository = RegisterRepository()

    suspend operator fun invoke(user: CreateUserRequest) : Result<UsernameValidateDTO> {
        val result = repository.createUser(user)

        //En caso de existir acá debe estar la lógica de negocio
        return result
    }
}