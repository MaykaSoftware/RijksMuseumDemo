package com.feature.topbar

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.PermIdentity
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core.common.constants.TopBarConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    actions: List<TopBarData>,
    modifier: Modifier = Modifier
) {
    var showDropDownMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            if (actions.size > 2) {
                IconButton(
                    onClick = { showDropDownMenu = true }) {
                    Icon(Icons.Filled.MoreVert, null)

                }
                DropdownMenu(showDropDownMenu, { showDropDownMenu = false }) {
                    actions.forEach { additionalAction ->
                        ComposeMenuItem(additionalAction.stringRes) { additionalAction.action }
                    }
                }
            } else {
                // If fewer than 3 actions, show them directly in the top app bar
                actions.forEach { action ->
                    action.action(this@TopAppBar)
                }
            }

        },
        scrollBehavior = scrollBehavior
    )
}

fun calculateDynamicActions(title: TopBarConstants): List<TopBarData> {
    return when (title) {
        TopBarConstants.NONE -> emptyList()
        TopBarConstants.HOME -> {
            listOf(
                TopBarData(
                    {
                        IconButton(
                            onClick = { /* Handle action click */ },
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PermIdentity,
                                contentDescription = "Profile"
                            )
                        }
                    }, R.string.topbar_profile
                )
            )
        }

        TopBarConstants.ART -> {
            listOf(
                TopBarData({
                    IconButton(
                        onClick = { /* Handle action click */ },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                }, R.string.topbar_Search),
                TopBarData({
                    IconButton(
                        onClick = { /* Handle action click */ },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }, R.string.topbar_Search)
            )
        }

        TopBarConstants.ART_DETAIL -> {
            listOf(
                TopBarData({
                    IconButton(
                        onClick = { /* Handle action click */ },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                }, R.string.topbar_Share),
                TopBarData({
                    IconButton(
                        onClick = { /* Handle action click */ },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "Save"
                        )
                    }
                }, R.string.topbar_Save)
            )
        }

        TopBarConstants.SETTINGS -> {
            listOf(
                TopBarData({
                    IconButton(
                        onClick = { /* Handle action click */ },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }, R.string.topbar_Settings),
                TopBarData({
                    IconButton(
                        onClick = { /* Handle action click */ },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Share"
                        )
                    }
                }, R.string.topbar_Share),
                TopBarData({
                    IconButton(
                        onClick = { /* Handle action click */ },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "Favorite"
                        )
                    }
                }, R.string.topbar_Favorites)

            )
        }
    }
}

@Composable
fun ComposeMenuItem(stringRes: Int, onClick: () -> @Composable() (RowScope.() -> Unit)) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Text(
            stringResource(id = stringRes), modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

data class TopBarData(
    val action: @Composable RowScope.() -> Unit,
    @StringRes val stringRes: Int
)