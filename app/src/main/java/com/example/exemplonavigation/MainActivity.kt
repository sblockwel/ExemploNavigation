package com.example.exemplonavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.exemplonavigation.ui.theme.ExemploNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExemploNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController()
    val items = listOf(
        ScreenManagger.Home,
        ScreenManagger.Profile,
        ScreenManagger.About
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = ScreenManagger.Home.route, Modifier.padding(innerPadding)) {
            composable(ScreenManagger.Home.route) { HomeCompose() }
            composable(ScreenManagger.Profile.route) { ProfileCompose(navController = navController) }
            composable(ScreenManagger.About.route){ AboutCompose() }
        }
    }
    /*
    Column() {
        Row() {
            Button(onClick = { navController.navigate("profile")}) {
                Text(text = "Profile")
            }
            Button(onClick = { navController.navigate("about")}) {
                Text(text = "About")
            }
        }
        NavHost(navController = navController, startDestination = "home") {
            composable("Home"){
                HomeCompose()
            }
            composable("Profile"){
                ProfileCompose(navController)
            }
            composable("About"){
                AboutCompose()
            }
        }
    }*/

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExemploNavigationTheme {
        MyApp()
    }
}