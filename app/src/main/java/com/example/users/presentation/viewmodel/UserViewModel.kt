package com.example.users.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.users.domain.model.User
import com.example.users.domain.repository.UserRepository
import com.example.users.presentation.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _usersState = MutableStateFlow<UserState>(UserState.Loading)
    val usersState: StateFlow<UserState> = _usersState.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _usersState.value = UserState.Loading

            userRepository.getUsers(forceRefresh).fold(
                onSuccess = { users ->
                    _usersState.value = UserState.Success(users)
                },
                onFailure = { exception ->
                    _usersState.value = UserState.Error(
                        message = exception.message ?: "Failed to load users"
                    )
                }
            )
        }
    }

    fun selectUser(user: User) {
        val currentState = _usersState.value
        if (currentState is UserState.Success) {
            _usersState.value = currentState.copy(selectedUser = user)
        }
    }

    fun clearError() {
        val currentState = _usersState.value
        if (currentState is UserState.Error) {
            loadUsers()
        }
    }
}