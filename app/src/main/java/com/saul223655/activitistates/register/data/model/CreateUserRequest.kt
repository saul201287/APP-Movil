package com.saul223655.activitistates.register.data.model

data class CreateUserRequest(
    val username: String,
    val password: String,
    val name: String,
    val lastname: String
)