package com.luishenrique.cutecatsgallery.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luishenrique.cutecatsgallery.home.domain.model.Gallery
import org.koin.android.ext.android.inject

class HomeActivity : ComponentActivity() {

    private val viewModel by inject<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    GalleryCats(listOf(10))
                }
            }
        }
    }
}

@Composable
fun GalleryCats(items: List<Gallery>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items.size) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                modifier = Modifier.size(width = 64.dp, height = 256.dp)
                    .padding(8.dp),
            ) {
                Text(text = "Item $it", textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    Surface(modifier = Modifier.fillMaxSize()) {
        GalleryCats(listOf(10))
    }
}