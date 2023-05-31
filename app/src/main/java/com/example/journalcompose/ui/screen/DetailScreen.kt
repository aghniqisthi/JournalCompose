package com.example.journalcompose.ui.screen

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.journalcompose.R
import com.example.journalcompose.model.Injection
import com.example.journalcompose.ui.component.DetailItem
import com.example.journalcompose.ui.theme.JournalComposeTheme
import com.example.journalcompose.viewmodel.DetailViewModel
import com.example.journalcompose.viewmodel.ViewModelFactory

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToFav: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getJournalById(id)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailItem(
                    data.journal.title,
                    data.journal.volume,
                    data.journal.desc,
                    data.journal.link,
                    data.journal.image,
                    data.status,
                    onBackClick = navigateBack,
                    onClickToFav = {
                        if(!data.status) viewModel.addFav(data.journal, false)
                        else viewModel.addFav(data.journal, true)
                        navigateToFav()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    JournalComposeTheme {
        DetailItem(
            "Information Systems",
            "Volume 10",
            "description journal lorem ipsum",
            "www.dicoding.com",
            R.drawable.information_systems,
            false,
            onBackClick = {},
            onClickToFav = {}
        )
    }
}