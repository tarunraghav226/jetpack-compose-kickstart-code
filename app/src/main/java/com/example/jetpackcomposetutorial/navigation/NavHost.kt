package com.example.jetpackcomposetutorial.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcomposetutorial.ui.composables.ScreenBye
import com.example.jetpackcomposetutorial.ui.composables.ScreenHello

@Composable
fun CustomNavHost(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "hello"){
        composable("hello"){
            ScreenHello(navController)
        }
        composable("bye"){
            ScreenBye(navController)
        }
    }
}