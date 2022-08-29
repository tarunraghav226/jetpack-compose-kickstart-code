package com.example.jetpackcomposetutorial.models

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val name: String,
    val id: String,
    val icon: ImageVector,
    val contentDescription: String? = null
)
