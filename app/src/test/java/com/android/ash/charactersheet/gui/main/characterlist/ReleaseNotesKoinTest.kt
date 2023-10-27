package com.android.ash.charactersheet.gui.main.characterlist

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

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
        val activity: AppCompatActivity = mock()
        whenever(activity.resources).thenReturn(resources)
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.oldVersion).doReturn(86)
        gameSystemHolder.dndv35DbHelper = dbHelper

        // Act
        val releaseNotes = ReleaseNotes(activity).getReleaseNotes()

        // Assert
        assertThat(releaseNotes).isEqualTo("myReleaseNote\nmyReleaseNote\n")
    }

    @Test
    fun isShowReleaseNotes_isUpgradeInDnDv35DatabaseAndNotShown_showReleaseNotesTrue() {
        // Arrange
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).doReturn(true)
        gameSystemHolder.dndv35DbHelper = dbHelper

        // Act
        val showReleaseNotes = ReleaseNotes(mock()).isShowReleaseNotes()

        // Assert
        assertThat(showReleaseNotes).isTrue
    }

    @Test
    fun isShowReleaseNotes_isUpgradeInDnD5eDatabaseAndNotShown_showReleaseNotesTrue() {
        // Arrange
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).doReturn(true)
        gameSystemHolder.dnd5eDbHelper = dbHelper

        // Act
        val showReleaseNotes = ReleaseNotes(mock()).isShowReleaseNotes()

        // Assert
        assertThat(showReleaseNotes).isTrue
    }

    @Test
    fun isShowReleaseNotes_isUpgradeInPathfinderDatabaseAndNotShown_showReleaseNotesTrue() {
        // Arrange
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).doReturn(true)
        gameSystemHolder.pathfinderDbHelper = dbHelper

        // Act
        val showReleaseNotes = ReleaseNotes(mock()).isShowReleaseNotes()

        // Assert
        assertThat(showReleaseNotes).isTrue
    }

    @Test
    fun isShowReleaseNotes_isUpgradeAndIsShown_showReleaseNotesFalse() {
        // Arrange
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).doReturn(true)
        val underTest = ReleaseNotes(mock())
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
        val showReleaseNotes = ReleaseNotes(mock()).isShowReleaseNotes()

        // Assert
        assertThat(showReleaseNotes).isFalse
    }

}