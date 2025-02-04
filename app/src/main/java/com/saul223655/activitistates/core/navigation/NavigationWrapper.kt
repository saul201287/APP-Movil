package com.saul223655.activitistates.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saul223655.activitistates.login.presentation.LoginScreen
import com.saul223655.activitistates.login.presentation.LoginViewModel
import com.saul223655.activitistates.menu.presentation.MenuScreen
import com.saul223655.activitistates.menu.presentation.MenuViewModel
import com.saul223655.activitistates.register.presentation.RegisterScreen
import com.saul223655.activitistates.register.presentation.RegisterViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                loginViewModel = LoginViewModel(),
                navigateToHome = { navController.navigate(Register) },
                navigateToMenu = {navController.navigate(Menu)}
            )
        }
        composable<Register> {
            RegisterScreen(RegisterViewModel()) {
                navController.popBackStack()
            }
        }
        composable<Menu> {
            MenuScreen(MenuViewModel())
        }
    }
}