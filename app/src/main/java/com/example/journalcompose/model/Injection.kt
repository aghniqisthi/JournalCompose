package com.example.journalcompose.model

object Injection {
    fun provideRepository(): JournalRepository {
        return JournalRepository.getInstance()
    }
}