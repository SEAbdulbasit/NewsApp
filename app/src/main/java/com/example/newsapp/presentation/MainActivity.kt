package com.example.newsapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.biometric.BiometricAuthenticationHelper
import com.example.newsapp.biometric.BiometricState
import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.presentation.details.HeadlinesDetailsRoute
import com.example.newsapp.presentation.details.NewsHeadlineDetailsViewModel
import com.example.newsapp.presentation.details.NewsHeadlineDetailsViewModelFactory
import com.example.newsapp.presentation.headlineslist.NewsHeadlineViewModel
import com.example.newsapp.presentation.headlineslist.NewsHeadlinesRoute
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


private const val HeadlinesList = "list"
private const val HeadlinesDetails = "details"

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val biometricHelper = BiometricAuthenticationHelper(this)

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsAppTheme {
                val biometricState = remember { mutableStateOf(BiometricState.UNINITIALIZED) }

                if (biometricHelper.isBiometricAvailable()) {
                    biometricHelper.showBiometricPrompt(
                        onSuccess = {
                            biometricState.value = BiometricState.AUTH_SUCCESS
                        },
                        onFailure = {
                            biometricState.value = BiometricState.AUTH_FAILED
                        },
                        onAuthenticationError = { errorCode, errorString ->
                            biometricState.value = BiometricState.AUTH_ERROR
                        }
                    )
                } else {
                    App()
                }

                when (biometricState.value) {
                    BiometricState.AUTH_SUCCESS -> {
                        App()
                    }

                    BiometricState.AUTH_FAILED -> {
                        this.finish()
                    }

                    BiometricState.AUTH_ERROR -> {
                        this.finish()
                    }

                    BiometricState.UNINITIALIZED -> {
                    }
                }
            }
        }
    }

    @Composable
    private fun App(
    ) {

        //As currently, we can't send parcelable in navigation so setting the selected value to this
        // and then passing it to details
        val selectedArticle = remember { (mutableStateOf<ArticleDomainModel?>(null)) }

        val navController = rememberNavController()
        val startRoute = HeadlinesList

        NavHost(navController, startDestination = startRoute) {
            composable(HeadlinesList) { backStackEntry ->
                val viewModel = hiltViewModel<NewsHeadlineViewModel>()
                NewsHeadlinesRoute(viewModel) {
                    selectedArticle.value = it
                    navController.navigate(HeadlinesDetails)
                }
            }
            composable(HeadlinesDetails) { backStackEntry ->
                val viewModel: NewsHeadlineDetailsViewModel =
                    viewModel(factory = NewsHeadlineDetailsViewModelFactory(selectedArticle.value!!))
                HeadlinesDetailsRoute(viewModel) { navController.popBackStack() }
            }
        }
    }

}


