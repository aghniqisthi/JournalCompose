package com.example.journalcompose.ui.screen

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailHome : Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
    object DetailFav : Screen("favorite/{id}") {
        fun createRoute(id: Int) = "favorite/$id"
    }
}
