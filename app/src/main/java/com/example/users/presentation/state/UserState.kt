package com.example.users.presentation.state

import com.example.users.domain.model.User

sealed class UserState {
    data object Loading : UserState()
    data class Success(
        val users: List<User>,
        val selectedUser: User? = null
    ) : UserState()
    data class Error(val message: String) : UserState()
}