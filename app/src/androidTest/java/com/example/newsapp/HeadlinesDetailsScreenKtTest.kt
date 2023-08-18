package com.example.newsapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.domain.model.SourceDomainModel
import com.example.newsapp.presentation.details.HeadlinesDetailsScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by abdulbasit on 18/08/2023.
 */

@RunWith(AndroidJUnit4::class)
class HeadlinesDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHeadlinesDetailsScreen() {
        val article = ArticleDomainModel(
            "Title",
            "Author",
            "Date",
            "URL",
            SourceDomainModel("id", "name"),
            "totle",
            "url",
            "url to image 1"
        )

        composeTestRule.setContent {
            HeadlinesDetailsScreen(article = article, onBackClicked = {})
        }

        composeTestRule.waitForIdle()

        // Verify the presence of various UI components
        composeTestRule.onNodeWithContentDescription("back arrow").assertExists()
        composeTestRule.onNodeWithText(article.title).assertExists()
        composeTestRule.onNodeWithContentDescription("headline image").assertExists()
        composeTestRule.onNodeWithText(article.description).assertExists()
        composeTestRule.onNodeWithText(article.content).assertExists()
    }
}
