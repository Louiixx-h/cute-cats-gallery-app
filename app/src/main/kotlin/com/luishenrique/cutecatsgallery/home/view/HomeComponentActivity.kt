package com.luishenrique.cutecatsgallery.home.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class HomeComponentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    Greeting("Hello World!")
                }
            }
        }
    }
}

@Composable
fun Greeting(message: String) {
    Text(text = message)
}

@Preview
@Composable
fun PreviewHome() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Greeting(message = "Hello World!")
    }
}