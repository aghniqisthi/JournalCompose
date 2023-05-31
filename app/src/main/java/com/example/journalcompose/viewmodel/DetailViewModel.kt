package com.example.journalcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journalcompose.model.Favorite
import com.example.journalcompose.model.Journal
import com.example.journalcompose.model.JournalRepository
import com.example.journalcompose.ui.screen.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: JournalRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Favorite>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Favorite>> get() = _uiState

    fun getJournalById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFavoriteById(id))
        }
    }

    fun addFav(journal: Journal, status: Boolean) {
        viewModelScope.launch {
            repository.updateFavorite(journal.id, status)
        }
    }
}