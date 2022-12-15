package com.example.askdaktari.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import com.example.askdaktari.ui.theme.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.askdaktari.ui.theme.clinics.MapScreen
import com.example.askdaktari.ui.theme.home.BottomNavBar
import com.example.askdaktari.ui.theme.home.HomeScreen
import com.example.askdaktari.ui.theme.home.Items
import com.example.askdaktari.ui.theme.home.TopNavBar
import com.example.askdaktari.ui.theme.media.FolderScreen
import com.example.askdaktari.ui.theme.settings.ProfileScreen

enum class NavPaths{
    TopNavBar,
    FoldersView,
    BottomNavBar
}

@Composable
fun Home(navController: NavHostController = rememberNavController()) {
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopNavBar(navBarName = "Home")
            HomePage(navController = navController)
            BottomNavBar(navController = navController)
        }
    }
}

@Composable
fun HomePage(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Items.Home.id) {
        composable(route = Items.Home.id) {
            HomeScreen()
        }
        composable(route = Items.Chats.id) {
            FolderScreen()
        }
        composable(route = Items.Clinics.id) {
            MapScreen()
        }
        composable(route = Items.Profile.id) {
            ProfileScreen()
        }

    }
}