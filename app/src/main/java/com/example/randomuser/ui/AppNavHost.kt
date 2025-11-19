package com.example.randomuser.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

enum class Screen {
    UserMain,
    UserDetail,
    UserGenerate
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.UserMain.name) {
        composable(route = Screen.UserMain.name) {
            UserMainScreen(
                goToGenerateScreen = { navController.navigate(route = Screen.UserGenerate.name) },
                goToDetailsScreen = { id -> navController.navigate(route = Screen.UserDetail.name + "/$id") })
        }
        composable(
            route = Screen.UserDetail.name + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("id")!!
            UserDetailScreen(userId)
        }
        composable(route = Screen.UserGenerate.name) {
            UserGenerateScreen(
                goback = { navController.popBackStack() }
            )
        }
    }
}