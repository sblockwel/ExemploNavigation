package com.example.exemplonavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ProfileCompose(navController: NavHostController) {
    Column() {
        Text(text = "Profile")
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Voltar")
        }
        Button(onClick = { navController.navigate("about") }) {
            Text(text = "About")
        }
    }
}