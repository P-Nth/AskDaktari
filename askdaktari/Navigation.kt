package com.example.askdaktari

import com.example.askdaktari.home.Home
import com.example.askdaktari.login.Login
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.askdaktari.ui.theme.LoginScreen
import androidx.navigation.compose.rememberNavController

enum class LoginPaths{
    Login,
    Register
}

enum class HomePaths{
    Home,
    Chatroom,
    VideoCall
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    login: Login
) {
    NavHost(navController = navController, startDestination = LoginPaths.Login.name) {
        // Login
        composable(route = LoginPaths.Login.name) {
            LoginScreen(onNavToHomeScreen = {
                navController.navigate(HomePaths.Home.name) {
                    launchSingleTop = true
                    popUpTo(route = LoginPaths.Login.name) {
                        inclusive = true
                    }
                }
            },
                login = login
            ) {
                navController.navigate(LoginPaths.Register.name) {
                    launchSingleTop = true
                    popUpTo(route = LoginPaths.Login.name) {
                        inclusive = true
                    }
                }
            }
        }
        // Register
        composable(route = LoginPaths.Register.name) {
            LoginScreen(onNavToHomeScreen = {
                navController.navigate(HomePaths.Home.name) {
                    popUpTo(route = LoginPaths.Register.name) {
                        inclusive = true
                    }
                }
            },
                login = login
            ) {
                navController.navigate(LoginPaths.Login.name)
            }
        }
        // HomeScreen
        composable(route = HomePaths.Home.name) {
            Home()
        }

    }
}
