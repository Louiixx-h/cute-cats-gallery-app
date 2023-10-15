package com.luishenrique.cutecatsgallery.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luishenrique.cutecatsgallery.routes.Routes
import org.koin.android.ext.android.inject

class HomeActivity : ComponentActivity() {

    private val viewModel by inject<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.Home.route) {
                        composable(Routes.Home.route) {
                            HomeScreen(viewModel.uiState) { viewModel.getCats() }
                        }
                    }
                }
            }
        }
    }
}