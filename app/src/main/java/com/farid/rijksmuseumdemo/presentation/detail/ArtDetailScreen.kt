package com.farid.rijksmuseumdemo.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.farid.rijksmuseumdemo.domain.model.detail.ArtObjectDetail

@Composable
fun ArtDetailsScreen(
    state: State
) {
    Box(
        modifier = Modifier.fillMaxSize(),

    ) {
        when (state) {
            is State.Error -> {
                ErrorMessage(state.errorMessage)
            }

            State.Loading -> {
                Box(Modifier.align(Alignment.Center)){
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