package com.example.users.domain.repository

import com.example.users.domain.model.User

interface UserRepository {
    suspend fun getUsers(forceRefresh: Boolean = false): Result<List<User>>
    suspend fun getUserById(id: String): Result<User>
}