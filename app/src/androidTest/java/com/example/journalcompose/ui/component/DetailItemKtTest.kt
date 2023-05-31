package com.example.journalcompose.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.journalcompose.R
import com.example.journalcompose.model.Favorite
import com.example.journalcompose.model.Journal
import com.example.journalcompose.ui.theme.JournalComposeTheme
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailItemKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val favoriteJournal = Favorite(1, false,
        Journal(
            1,
            "Information Systems",
            "Volume 115, May 2023",
            "Information systems are the software and hardware systems that support data-intensive applications. The journal Information Systems publishes articles concerning the design and implementation of languages, data models, process models, algorithms, software and hardware for information systems. Subject areas include data management issues as presented in the principal international database conferences (e.g., ACM SIGMOD/PODS, VLDB, ICDE and ICDT/EDBT) as well as data-related issues from the fields of data mining/machine learning, information retrieval coordinated with structured data, internet and cloud data management, business process management, web semantics, visual and audio information systems, scientific computing, and data science.",
            "https://www.sciencedirect.com/journal/information-systems",
            R.drawable.information_systems
        )
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JournalComposeTheme {
                DetailItem(
                    favoriteJournal.journal.title,
                    favoriteJournal.journal.volume,
                    favoriteJournal.journal.desc,
                    favoriteJournal.journal.link,
                    favoriteJournal.journal.image,
                    favoriteJournal.status,
                    onBackClick = {},
                    onClickToFav = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(favoriteJournal.journal.title).assertIsDisplayed()
    }

    @Test
    fun addFavorite_buttonEnabled() {
        composeTestRule.onNodeWithContentDescription("Favorite Button").assertIsEnabled()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.fav)).performClick()
    }
}