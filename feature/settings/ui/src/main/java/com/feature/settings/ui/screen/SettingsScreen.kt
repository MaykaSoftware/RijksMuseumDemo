package com.feature.settings.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(state: State, modifier: Modifier = Modifier) {
    Text(
        text = "Hello Settings",
        modifier = modifier
    )
}