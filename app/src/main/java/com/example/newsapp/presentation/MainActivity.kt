package com.example.newsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.headlineslist.NewsHeadlineViewModel
import com.example.newsapp.presentation.headlineslist.NewsHeadlinesRoute
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                val startRoute = "list"
                NavHost(navController, startDestination = startRoute) {
                    composable("list") { backStackEntry ->
                        val viewModel = hiltViewModel<NewsHeadlineViewModel>()
                        NewsHeadlinesRoute(viewModel)
                    }
                }
            }
        }
    }
}
