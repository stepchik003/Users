package com.example.users.data.remote

import com.example.users.domain.model.User

data class UserResponse(
    val results: List<User>
)