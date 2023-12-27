package com.feature.art_details.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.feature.art_details.ui.R
import com.feature.common.domain.model.art_detail.ArtObjectDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtDetailsScreen(
    state: State,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),

        ) {
        when (state) {
            is State.Error -> {
                ErrorMessage(state.errorMessage)
            }

            State.Loading -> {
                Box(Modifier.align(Alignment.Center)) {
                    CircularProgressIndicator()
                }
            }

            is State.Success -> {
                if (state.artObjectDetail != null) {
                    CardItem(artObjectDetail = state.artObjectDetail)
                }
            }
        }

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
fun CardItem(artObjectDetail: ArtObjectDetail) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = artObjectDetail.webImage.url,
            contentDescription = artObjectDetail.title,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(16.dp)

        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = artObjectDetail.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.testTag("title")
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = artObjectDetail.description ?: "",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ErrorMessage(errorMessage: String) {
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = errorMessage,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }
}