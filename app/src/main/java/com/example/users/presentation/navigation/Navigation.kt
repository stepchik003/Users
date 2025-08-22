package com.example.users.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.users.domain.model.User
import com.example.users.presentation.screens.UserDetailScreen
import com.example.users.presentation.screens.UserListScreen
import com.example.users.presentation.viewmodel.UserViewModel

@Composable
fun UserApp() {
    val navController = rememberNavController()
    val viewModel: UserViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "userList") {
        composable("userList") {
            UserListScreen(
                viewModel = viewModel,
                onUserClick = { user ->
                    viewModel.selectUser(user)
                    navController.navigate("userDetail")
                }
            )
        }
        composable("userDetail") {
            UserDetailScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}