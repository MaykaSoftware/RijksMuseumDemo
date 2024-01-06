package com.feature.art.ui.screen.art

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.core.common.model.art.ArtObject
import com.feature.art.ui.R
import java.util.UUID

sealed interface ListArtObjectUiState {
    data class ArtObjectItem(val artObject: ArtObject) : ListArtObjectUiState
    data class ArtHeaderItem(val header: String, val id: String = UUID.randomUUID().toString()) :
        ListArtObjectUiState
}

enum class MyListContentType { Header, Cell }

@Composable
fun ArtScreen(
    artObjects: LazyPagingItems<ListArtObjectUiState>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    ArtListScreen(artObjects = artObjects, navController, modifier)
}

@Composable
fun ArtListScreen(
    artObjects: LazyPagingItems<ListArtObjectUiState>,
    navController: NavController,
    modifier: Modifier
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
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