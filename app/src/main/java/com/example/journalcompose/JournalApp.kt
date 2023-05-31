package com.example.journalcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.journalcompose.ui.screen.*
import com.example.journalcompose.ui.theme.JournalComposeTheme
import com.example.journalcompose.ui.component.Header

@Composable
fun JournalApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != Screen.DetailHome.route) {
                Header(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailHome.createRoute(id))
                    }
                )
            }
        composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailFav.createRoute(id))
                    },
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = Screen.DetailHome.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("id") ?: -1
                DetailScreen(
                    id = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToFav = {
                        navController.popBackStack()
                        navController.navigate(Screen.Favorite.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(
                route = Screen.DetailFav.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("id") ?: -1
                DetailScreen(
                    id = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToFav = {
                        navController.popBackStack()
                        navController.navigate(Screen.Favorite.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JournalAppPreview() {
    JournalComposeTheme {
        JournalApp()
    }
}
