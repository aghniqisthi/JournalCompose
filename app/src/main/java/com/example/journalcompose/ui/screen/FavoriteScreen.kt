package com.example.journalcompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.journalcompose.R
import com.example.journalcompose.model.Injection
import com.example.journalcompose.ui.component.JournalItem
import com.example.journalcompose.viewmodel.FavoriteViewModel
import com.example.journalcompose.viewmodel.ViewModelFactory

@Composable
fun FavoriteScreen(
    navigateToDetail: (Int) -> Unit,
    navigateBack: () -> Unit,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedFavorites()
            }
            is UiState.Success -> {
                FavoriteContent(
                    uiState.data,
                    navigateToDetail = navigateToDetail,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {
                Text(uiState.errorMessage)
            }
        }
    }
}

@Composable
fun FavoriteContent(
    state: FavState,
    navigateToDetail: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
//                    .align(Alignment.Start)
            )
            Text(
                text = stringResource(id = R.string.count_fav, state.favorite.size),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.favorite, key = { it.id }) { item ->
                JournalItem(
                    id = item.journal.id,
                    title = item.journal.title,
                    volume = item.journal.volume,
                    image = item.journal.image,
                    modifier = Modifier.clickable {
                        navigateToDetail(item.journal.id)
                    },
                    navigateToDetail
                )
                Divider()
            }
        }
    }
}
