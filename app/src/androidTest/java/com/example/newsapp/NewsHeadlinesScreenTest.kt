package com.example.newsapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.domain.model.SourceDomainModel
import com.example.newsapp.presentation.NewsScreenState
import com.example.newsapp.presentation.headlineslist.HeadlinesScreen
import org.junit.Rule
import org.junit.Test


/**
 * Created by abdulbasit on 17/08/2023.
 */
class NewsHeadlinesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testHeadlinesScreen() {
        val articles = listOf(
            ArticleDomainModel(
                "Title 1",
                "Author 1",
                "Date 1",
                "URL 1",
                SourceDomainModel("1", "name 1"),
                "title 1",
                "url 1",
                "url to image 1"
            ),
            ArticleDomainModel(
                "Title 2",
                "Author 2",
                "Date 2",
                "URL 2",
                SourceDomainModel("2", "name 2"),
                "title 2",
                "url 2",
                "url to image 2"
            )
        )
        val uiState = NewsScreenState(
            articles = articles,
            isLoading = false,
            exceptionMessage = null
        )

        composeTestRule.setContent {
            HeadlinesScreen(uiState = uiState, onRefresh = {}, navigateToHeadlinesDetails = {})
        }

        composeTestRule.waitForIdle()

        //Verify that loading is not shown
        indicatorNode.assertIsNotDisplayed()

        // Verify that all article titles are displayed
        articles.forEach { article ->
            composeTestRule.onNodeWithText(article.title).assertIsDisplayed()
            composeTestRule.onNodeWithContentDescription(article.description).assertIsDisplayed()
            composeTestRule.onNodeWithText(article.publishedAt).assertIsDisplayed()
        }
    }

    @Test
    fun testHeadlinesLoadingScreen() {
        val articles = listOf(
            ArticleDomainModel(
                "Title 1",
                "Author 1",
                "Date 1",
                "URL 1",
                SourceDomainModel("1", "name 1"),
                "title 1",
                "url 1",
                "url to image 1"
            ),
            ArticleDomainModel(
                "Title 2",
                "Author 2",
                "Date 2",
                "URL 2",
                SourceDomainModel("2", "name 2"),
                "title 2",
                "url 2",
                "url to image 2"
            )
        )

        val uiState = NewsScreenState(
            articles = articles,
            isLoading = true,
            exceptionMessage = null
        )

        composeTestRule.setContent {
            HeadlinesScreen(uiState = uiState, onRefresh = {}, navigateToHeadlinesDetails = {})
        }

        //Verify that loading is shown
        indicatorNode.assertIsDisplayed()

        // Verify that all article titles are displayed
        articles.forEach { article ->
            composeTestRule.onNodeWithText(article.title).assertIsDisplayed()
            composeTestRule.onNodeWithContentDescription(article.description).assertIsDisplayed()
            composeTestRule.onNodeWithText(article.publishedAt).assertIsDisplayed()
        }
    }

    private val indicatorNode get() = composeTestRule.onNodeWithTag(IndicatorTag).onChild()

}

private const val IndicatorTag = "pull-refresh-indicator"