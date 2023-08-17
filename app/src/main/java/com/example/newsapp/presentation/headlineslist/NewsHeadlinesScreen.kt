package com.example.newsapp.presentation.headlineslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.domain.model.ArticleDomainModel
import com.example.newsapp.presentation.NewsScreenState


/**
 * Created by abdulbasit on 17/08/2023.
 */

@Composable
fun NewsHeadlinesRoute(
    viewModel: NewsHeadlineViewModel
) {
    val uiState: NewsScreenState by viewModel.uiState.collectAsState()

    HeadlinesScreen(uiState) { viewModel.onAction(NewsHeadlinesActions.Refresh) }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeadlinesScreen(uiState: NewsScreenState, onRefresh: () -> Unit) {
    val pullRefreshState = rememberPullRefreshState(uiState.isLoading, onRefresh)

    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(uiState.articles) {
                NewsHeadlineItem(article = it)
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}

@Composable
fun NewsHeadlineItem(
    article: ArticleDomainModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = article.title,
                    modifier = Modifier,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = article.author,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(start = 8.dp),
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = article.publishedAt,
                    modifier = Modifier
                        .padding(end = 8.dp),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

