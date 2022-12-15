package com.example.askdaktari.ui.theme.home


import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.rounded.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.askdaktari.ui.theme.Purple200

sealed class Items(
    val id:String,
    var title:String,
    val icon: ImageVector
) {

    object Home: Items("home", "Home", Icons.Rounded.Home)
    object Chats: Items("chats", "Chats", Icons.Rounded.Chat)
    object Clinics: Items("clinics", "Clinics", Icons.Rounded.LocalHospital)
    object Profile: Items("profile", "Profile", Icons.Rounded.Person)

    object ItemsIn{
        val screens = listOf(
            Home,Chats,Clinics,Profile
        )
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val currentScreen = remember { mutableStateOf<Items>(Items.Home) }

    BNavComponent(navController = navController)
}

@Composable
fun BNavComponent(
    navController: NavHostController,
    // currentScreenId: MutableState<Items>
) {
    val items = Items.ItemsIn.screens

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val selectedScreen = items.any { it.id == currentDestination?.route }
    
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (selectedScreen) {
            items.forEach { screen ->
                BNavComponents (
                    screen = screen,
                    navController = navController,
                    currentDestination = currentDestination
                    // isSelected = screen.id == currentScreenId
                )
            }
        }
    }
}

@Composable
fun BNavComponents (
    screen: Items,
    navController: NavHostController,
    currentDestination: NavDestination?,
    // isSelected: Boolean
) {

    val isSelected = currentDestination?.hierarchy?.any() { it.route == screen.id } == true
    val bgColor = if (isSelected) Purple200.copy(.2f) else Color.Transparent
    val contentColor = if (isSelected) Purple200 else MaterialTheme.colors.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(bgColor)
            .clickable(onClick = {
                navController.navigate(screen.id) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(imageVector = screen.icon, contentDescription = null, tint = contentColor)
            AnimatedVisibility(visible = isSelected) {
                Text(text = screen.title, color = contentColor)
            }
        }
    }
}
