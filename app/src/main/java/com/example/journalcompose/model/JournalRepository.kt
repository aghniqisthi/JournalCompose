package com.example.journalcompose.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class JournalRepository {

    private val favorite = mutableListOf<Favorite>()

    init {
        if (favorite.isEmpty()) {
            JournalsData.journals.forEach {
                favorite.add(Favorite(0, false, it))
            }
        }
    }

    fun getAllJournals(): Flow<List<Favorite>> {
        return flowOf(favorite)
    }

    fun getResultFavorite(): List<Favorite> {
        return favorite
    }

    fun searchJournal(query: String): List<Favorite>{
        return favorite.filter {
            it.journal.title.contains(query, ignoreCase = true)
        }
    }

    fun getFavoriteById(id: Int): Favorite {
        return favorite.first {
            it.journal.id == id
        }
    }

    fun updateFavorite(id: Int, status: Boolean): Flow<Boolean> {
        val index = favorite.indexOfFirst { it.journal.id == id }
        val result = if (!status) {
            val favIndex = favorite[index]
            favorite[index] = favIndex.copy(id = index, status = true, journal = favIndex.journal)
            true
        } else {
            val favIndex = favorite[index]
            favorite[index] = favIndex.copy(id = index, status = false, journal = favIndex.journal)
            false
        }
        return flowOf(result)
    }

    fun getAddedFavorites(): Flow<List<Favorite>> {
        return getAllJournals().map { journals ->
            journals.filter { journal ->
                journal.status
            }
        }
    }

    companion object {
        @Volatile
        private var instance: JournalRepository? = null

        fun getInstance(): JournalRepository =
            instance ?: synchronized(this) {
                JournalRepository().apply {
                    instance = this
                }
            }
    }

}