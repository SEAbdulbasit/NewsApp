package com.example.newsapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.presentation.details.HeadlinesDetailsRoute
import com.example.newsapp.presentation.details.NewsHeadlineDetailsViewModel
import com.example.newsapp.presentation.details.NewsHeadlineDetailsViewModelFactory
import com.example.newsapp.presentation.headlineslist.NewsHeadlineViewModel
import com.example.newsapp.presentation.headlineslist.NewsHeadlinesRoute
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val selectedArticle = mutableStateOf<ArticleDomainModel?>(null)
                val navController = rememberNavController()
                val startRoute = "list"
                NavHost(navController, startDestination = startRoute) {
                    composable("list") { backStackEntry ->
                        val viewModel = hiltViewModel<NewsHeadlineViewModel>()
                        NewsHeadlinesRoute(viewModel) {
                            selectedArticle.value = it
                            navController.navigate("details")
                        }
                    }
                    composable("details") { backStackEntry ->
                        val viewModel: NewsHeadlineDetailsViewModel =
                            viewModel(factory = NewsHeadlineDetailsViewModelFactory(selectedArticle.value!!))
                        HeadlinesDetailsRoute(viewModel) { navController.popBackStack() }
                    }
                }
            }
        }
    }
}
