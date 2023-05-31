package com.example.journalcompose.ui.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.journalcompose.model.Injection
import com.example.journalcompose.model.JournalRepository
import com.example.journalcompose.ui.component.JournalItem
import com.example.journalcompose.ui.component.SearchBar
import com.example.journalcompose.viewmodel.HomeViewModel
import com.example.journalcompose.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllJournals()
            }
            is UiState.Success -> {
                HomeContent(
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(JournalRepository())),
    ) {
    val searchResult by viewModel.journals.collectAsState()
    val query by viewModel.query

    val listState = rememberLazyListState()
    val showButton: Boolean by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    Box(modifier = modifier){
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        item {
            SearchBar(
                query = query,
                onQueryChange = viewModel::search,
                modifier = Modifier.background(MaterialTheme.colors.background)
            )
        }
        searchResult.forEach { (_, data) ->
            items(data, key = { it.journal.id }) { fav ->
                JournalItem(
                    id = fav.journal.id,
                    title = fav.journal.title,
                    volume = fav.journal.volume,
                    image = fav.journal.image,
                    modifier = Modifier
                        .animateItemPlacement(tween(durationMillis = 100))
                        .clickable {
                            navigateToDetail(fav.journal.id)
                        }
                        .fillMaxWidth(),
                    navigateToDetail
                )
            }
        }
    }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
//            ScrollToTopButton(
//                onClick = {
//                    scope.launch {
//                        listState.animateScrollToItem(index = 0)
//                    }
//                }
//            )
        }
    }
}