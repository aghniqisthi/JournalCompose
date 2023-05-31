package com.example.journalcompose.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.journalcompose.ui.screen.Screen
import com.example.journalcompose.R

@Composable
fun Header(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Text(text = stringResource(id = R.string.app_name), color = Color.Black)
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Screen.Favorite.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.favorite_page),
                    modifier = Modifier.size(30.dp, 30.dp),
                    tint = Color(0xFFE57373)
                )
            }
            IconButton(onClick = {
                navController.navigate(Screen.Profile.route)
            }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(id = R.string.about_page),
                    modifier = Modifier.size(30.dp, 30.dp),
                    tint = Color(0xFF7986CB)
                )
            }
        }
    )
}
