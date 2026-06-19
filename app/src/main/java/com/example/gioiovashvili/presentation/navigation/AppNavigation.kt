package com.example.gioiovashvili.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gioiovashvili.presentation.screen.home.HomeScreen
import com.example.gioiovashvili.presentation.screen.login.LoginScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = LoginRoute,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(30)) }) {

        composable<LoginRoute> {
            LoginScreen(onNavigateToHome = {
                navController.navigate(HomeRoute) {
                    popUpTo<LoginRoute> {
                        inclusive = true
                    }
                }
            })
        }

        composable<HomeRoute> {
            HomeScreen()
        }
    }
}

@Serializable
data object LoginRoute

@Serializable
data object HomeRoute