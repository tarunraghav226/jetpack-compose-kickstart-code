package com.example.jetpackcomposetutorial.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpackcomposetutorial.R

@Composable
fun ScreenHello(navHostController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "HELLO",
            color = Color.Blue,
            modifier = Modifier.clickable {
                navHostController.navigate("bye")
            }
        )
    }
}

@Composable
fun ScreenBye(
    navHostController: NavHostController
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Bye",
            color = Color.Red,
            modifier = Modifier.clickable {
                navHostController.popBackStack()
            }
        )
    }
}

@Composable
fun BottomNavigationView(
    navController: NavController
){
    val items = listOf(
        "hello",
        "bye"
    )
    
    BottomNavigation(
        backgroundColor = Color.Cyan,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach{
            BottomNavigationItem(
                icon = {Icon(painterResource(id = R.drawable.ic_baseline_account_circle_24), contentDescription = it)},
                selected = currentRoute == it,
                label = { Text(text = it, fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                onClick = {
                    navController.navigate(it) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}