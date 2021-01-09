package com.android.ash.charactersheet.gui.main.characterlist

import android.content.res.Resources
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class ReleaseNotesKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getReleaseNotes_releaseWithVersionCode_returnsReleaseNotes() {
        // Arrange
        val resources: Resources = mock()
        whenever(resources.getString(any())).doReturn("myReleaseNote\n")
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.oldVersion).doReturn(65)
        gameSystemHolder.dndDbHelper = dbHelper

        // Act
        val releaseNotes = ReleaseNotes().getReleaseNotes(resources)

        // Assert
        assertThat(releaseNotes).isEqualTo("myReleaseNote\nmyReleaseNote\n")
    }

    @Test
    fun isShowReleaseNotes_isUpgradeAndNotShown_showReleaseNotesTrue() {
        // Arrange
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).doReturn(true)
        gameSystemHolder.dndDbHelper = dbHelper

        // Act
        val showReleaseNotes = ReleaseNotes().isShowReleaseNotes()

        // Assert
        assertThat(showReleaseNotes).isTrue
    }

    @Test
    fun isShowReleaseNotes_isUpgradeAndIsShown_showReleaseNotesFalse() {
        // Arrange
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).doReturn(true)
        val underTest = ReleaseNotes()
        underTest.isShowReleaseNotes()

        // Act
        val showReleaseNotes = underTest.isShowReleaseNotes()

        // Assert
        assertThat(showReleaseNotes).isFalse
    }

    @Test
    fun isShowReleaseNotes_noUpgrade_showReleaseNotesFalse() {
        // Arrange
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).doReturn(false)

        // Act
        val showReleaseNotes = ReleaseNotes().isShowReleaseNotes()

        // Assert
        assertThat(showReleaseNotes).isFalse
    }

}