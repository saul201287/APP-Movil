package com.saul223655.activitistates.login.domain

import com.saul223655.activitistates.login.data.model.LoginUserRequest
import com.saul223655.activitistates.register.data.model.UsernameValidateDTO
import com.saul223655.activitistates.login.data.repository.LoginRepository

class LoginUserUseCase {
    private  val repository = LoginRepository()

    suspend operator fun invoke(user: LoginUserRequest) : Result<UsernameValidateDTO> {
        val result = repository.validateUsername(user)
        //En caso de existir acá debe estar la lógica de negocio
        return result
    }
}