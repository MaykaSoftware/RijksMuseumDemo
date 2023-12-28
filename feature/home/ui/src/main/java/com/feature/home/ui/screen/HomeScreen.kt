package com.feature.home.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(state: State, modifier: Modifier = Modifier) {
    Text(
        text = "Hello Home",
        modifier = modifier
    )
}