package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import com.example.rickandmorty.presentation.ui.theme.Blue1

@Composable
fun BottomBar(
    tabs: Array<BottomBarTab>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    NavigationBar(
    ) {
        tabs.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "icon",
                        tint = Blue1
                    )
                },
                selected = currentRoute == item.route,
                onClick = { navigateToRoute(item.route) }
            )
        }
    }
}