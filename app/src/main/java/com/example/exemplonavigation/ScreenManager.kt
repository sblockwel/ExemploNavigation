package com.example.exemplonavigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenManagger(val route: String,
                            @StringRes val resourceId: Int,
                            val icon: ImageVector
) {
    object Home: ScreenManagger("Home", R.string.home, Icons.Filled.Home)
    object Profile : ScreenManagger("profile", R.string.profile, Icons.Filled.Build)
    object About : ScreenManagger("about", R.string.about, Icons.Filled.Face)
}