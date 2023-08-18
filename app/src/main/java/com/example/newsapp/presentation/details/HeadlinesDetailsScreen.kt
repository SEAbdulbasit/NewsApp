package com.example.newsapp.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.domain.model.ArticleDomainModel


/**
 * Created by abdulbasit on 18/08/2023.
 */

@Composable
fun HeadlinesDetailsRoute(
    viewModel: NewsHeadlineDetailsViewModel,
    onBackClicked: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    HeadlinesDetailsScreen(state, onBackClicked)
}


@Composable
fun HeadlinesDetailsScreen(
    article: ArticleDomainModel, onBackClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(
                R.string.back_arrow
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable(onClick = { onBackClicked() })
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp),
            fontWeight = FontWeight.W700
        )

        AsyncImage(
            model = article.urlToImage,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.headline_image),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .clip(RoundedCornerShape(16.dp))
        )

        Text(
            text = article.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}