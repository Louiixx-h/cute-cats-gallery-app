package com.luishenrique.cutecatsgallery.home.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luishenrique.cutecatsgallery.R
import com.luishenrique.cutecatsgallery.base.ErrorContent
import com.luishenrique.cutecatsgallery.commonComponents.Toolbar
import com.luishenrique.cutecatsgallery.home.domain.model.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    state: StateFlow<GalleryUiState>,
    onRefresh: () -> Unit = {}
) {
    val homeUiState by state.collectAsState()
    val refreshing = remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(refreshing.value, {
        refreshing.value = true
        onRefresh.invoke()
    })

    Scaffold(
        topBar = {
            Toolbar { Text("Cute Cats Gallery") }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState),
            contentAlignment = Alignment.Center
        ) {
            when (homeUiState) {
                is GalleryUiState.Loading -> {
                    LoadingIndicator()
                }

                is GalleryUiState.Content -> {
                    GalleryList(
                        images = (homeUiState as GalleryUiState.Content).images,
                        modifier = Modifier.padding(it)
                    )
                    refreshing.value = false
                }

                is GalleryUiState.Error -> {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cat),
                        contentDescription = stringResource(R.string.sad_cat)
                    )
                    refreshing.value = false
                }
            }
        }
    }
}

@Composable
fun GalleryList(images: List<Image>, modifier: Modifier = Modifier) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(images.count(), key = { it }) {
            GalleryCard(item = images.getOrNull(it))
        }
    }
}

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        trackColor = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun GalleryCard(item: Image?) {
    val showShimmer = remember { mutableStateOf(true) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        AsyncImage(
            model = item?.imageURL,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            error = painterResource(id = R.drawable.ic_cat_loading),
            onSuccess = { showShimmer.value = false },
            onError = { showShimmer.value = false },
            modifier = Modifier
                .background(
                    shimmerBrush(
                        targetValue = 1300f,
                        showShimmer = showShimmer.value
                    )
                )
        )
    }
}

@Composable
fun shimmerBrush(showShimmer: Boolean = true, targetValue: Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            ),
            label = ""
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}

@Preview
@Composable
fun PreviewErrorHome() {
    val stateMock = MutableStateFlow(GalleryUiState.Error(ErrorContent("", Throwable())))
    Surface(modifier = Modifier.fillMaxSize()) {
        HomeScreen(stateMock)
    }
}

@Preview
@Composable
fun PreviewLoadingHome() {
    val stateMock = MutableStateFlow(GalleryUiState.Loading)
    Surface(modifier = Modifier.fillMaxSize()) {
        HomeScreen(stateMock)
    }
}

@Preview
@Composable
fun PreviewContentHome() {
    val images = listOf(
        Image(),
        Image(),
        Image(),
        Image(),
        Image(),
    )
    val stateMock = MutableStateFlow(GalleryUiState.Content(images))
    Surface(modifier = Modifier.fillMaxSize()) {
        HomeScreen(stateMock)
    }
}