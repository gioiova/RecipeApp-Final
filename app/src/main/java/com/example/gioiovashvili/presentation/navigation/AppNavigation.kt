package com.example.gioiovashvili.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gioiovashvili.presentation.screen.about.AboutScreen
import com.example.gioiovashvili.presentation.screen.addrecipe.AddRecipeScreen
import com.example.gioiovashvili.presentation.screen.details.RecipeDetailsScreen
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
            HomeScreen(
                onNavigateToLogin = {
                    navController.navigate(LoginRoute) {
                        popUpTo<HomeRoute> { inclusive = true }
                    }
                },
                onNavigateToAbout = {
                    navController.navigate(AboutRoute)
                },
                onNavigateToAddRecipe = {
                    navController.navigate(AddRecipeRoute)
                },
                onNavigateToDetails = { recipeId ->
                    navController.navigate(RecipeDetailsRoute(recipeId))
                }
            )
        }

        composable<AboutRoute> {
            AboutScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable<AddRecipeRoute> {
            AddRecipeScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable<RecipeDetailsRoute> {
            RecipeDetailsScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}

@Serializable
data object LoginRoute

@Serializable
data object HomeRoute

@Serializable
data object AboutRoute

@Serializable
data object AddRecipeRoute

@Serializable
data class RecipeDetailsRoute(val recipeId: String)