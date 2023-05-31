package com.example.journalcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journalcompose.model.Favorite
import com.example.journalcompose.model.JournalRepository
import com.example.journalcompose.ui.screen.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: JournalRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Favorite>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Favorite>>> get() = _uiState

    private val _journals = MutableStateFlow(repository.getResultFavorite()
        .sortedBy { it.journal.title }
        .groupBy { it.journal.title[0] }
    )
    val journals: StateFlow<Map<Char, List<Favorite>>> get() = _journals

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _journals.value = repository.searchJournal(_query.value)
            .sortedBy { it.journal.title }
            .groupBy { it.journal.title[0] }
    }

    fun getAllJournals() {
        viewModelScope.launch {
            repository.getAllJournals()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}