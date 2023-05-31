package com.example.journalcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journalcompose.model.JournalRepository
import com.example.journalcompose.ui.screen.FavState
import com.example.journalcompose.ui.screen.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (private val repository: JournalRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<FavState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavState>> get() = _uiState

    fun getAddedFavorites() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedFavorites().collect {
                _uiState.value = UiState.Success(FavState(it))
            }
        }
    }
}