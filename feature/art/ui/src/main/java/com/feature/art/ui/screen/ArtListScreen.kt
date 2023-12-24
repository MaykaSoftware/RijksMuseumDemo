package com.feature.art.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.feature.art.ui.R
import com.feature.common.domain.model.art.ArtObject
import java.util.UUID

sealed interface ListArtObjectUiState {
    data class ArtObjectItem(val artObject: ArtObject) : ListArtObjectUiState
    data class ArtHeaderItem(val header: String, val id: String = UUID.randomUUID().toString()) :
        ListArtObjectUiState
}

enum class MyListContentType { Header, Cell }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtScreen(artObjects: LazyPagingItems<ListArtObjectUiState>, navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ArtAppBar(
                title = "ART SCREEN",
                scrollBehavior,
                canNavigateBack = false,
                navigateUp = {  }
            )
        }
    ) { innerPadding ->
        ArtListScreen(artObjects = artObjects,navController, innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
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
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun ArtListScreen(
    artObjects: LazyPagingItems<ListArtObjectUiState>,
    navController: NavController,
    innerPadding: PaddingValues
) {

    LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        items(
            count = artObjects.itemCount,
            key = artObjects.itemKey {
                when (it) {
                    is ListArtObjectUiState.ArtHeaderItem -> {
                        "header_${it.header}+${it.id}"
                    }

                    is ListArtObjectUiState.ArtObjectItem -> {
                        "Object_${it.artObject.id}"
                    }
                }
            },
            contentType = artObjects.itemContentType {
                when (it) {
                    is ListArtObjectUiState.ArtHeaderItem -> {
                        MyListContentType.Header
                    }

                    is ListArtObjectUiState.ArtObjectItem -> {
                        MyListContentType.Cell
                    }
                }
            }
        ) { index ->
            when (val result = artObjects[index]) {
                is ListArtObjectUiState.ArtHeaderItem -> {
                    ArtObjectHeader(result.header)
                }

                is ListArtObjectUiState.ArtObjectItem -> {
                    ArtObjectItem(
                        artObject = result.artObject,
                        onClick = {
                            artObjects[index]?.let {
                                navController.navigate("art_details/${result.artObject.objectNumber}")
                            }
                        }
                    )
                }

                null -> {

                }
            }

        }
        artObjects.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(Modifier.fillMaxSize()) {
                            Column(Modifier.align(Alignment.Center)) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = artObjects.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = artObjects.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}

@Composable
private fun ArtObjectHeader(author: String) {
    Row(Modifier.fillMaxWidth()) {
        Text(
            text = author,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color.Black.copy(alpha = 0.8f)
            ),
            modifier = Modifier
                .wrapContentSize()
                .padding(5.dp)
        )
    }
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(id = R.string.strRetry))
        }
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}