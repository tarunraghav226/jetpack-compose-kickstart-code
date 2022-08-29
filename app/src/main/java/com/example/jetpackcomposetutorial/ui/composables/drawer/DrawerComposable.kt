package com.example.jetpackcomposetutorial.ui.composables.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposetutorial.models.MenuItem

@Composable
fun DrawerView(
    navController: NavController,
    menuItems: List<MenuItem>
){
    Column(modifier = Modifier.fillMaxWidth()) {
        DrawerHeader()
        DrawerItems(menuItems = menuItems, navController = navController)
    }
}

@Composable
fun DrawerItems(menuItems: List<MenuItem>, navController: NavController){
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(menuItems){
            item -> 
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                            navController.navigate(item.id){
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
            ){
                Icon(imageVector = item.icon, contentDescription = item.contentDescription)

                Spacer(modifier = Modifier.padding(horizontal = 24.dp))
                Text(text = item.name)
            }
        }
    }
}

@Composable
fun DrawerHeader(){
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 60.dp),
    ){
        Text(
            text = "Your image here",
            modifier = Modifier.fillMaxHeight(),
            fontSize = 60.sp
        )
    }
    
}