package com.android.ash.charactersheet.gui.main.characterlist

import android.content.Context
import android.content.SharedPreferences
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.*

class CharacterListGameSystemKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<PreferenceServiceHolder>()
        declareMock<FirebaseAnalytics>()
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun onCreateGameSystem_gameSystemExists_doNothing() {
        // Arrange
        gameSystemHolder.gameSystem = mock()

        // Act
        CharacterListGameSystem().onCreateGameSystem(mock())

        // Assert
        verifyNoMoreInteractions(preferenceServiceHolder)
    }

    @Test
    fun onCreateGameSystem_gameSystemIsNull_createDBsAndPreferencesService() {
        // Arrange
        val context: Context = mock()
        val preferences: SharedPreferences = mock()
        whenever(context.getSharedPreferences(any(), any())).doReturn(preferences)
        whenever(preferences.edit()).doReturn(mock())

        // Act
        CharacterListGameSystem().onCreateGameSystem(context)

        // Assert
        verify(preferenceServiceHolder).preferenceService = any()
    }

    @Test
    fun onResumeGameSystem_gameSystemIsNull_createDBsAndGameSystem() {
        // Arrange
        val preferenceService: PreferenceService = mock()
        whenever(preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE)).doReturn(0)
        whenever(preferenceServiceHolder.preferenceService).doReturn(preferenceService)
        val characterListActivity: CharacterListActivity = mock()

        // Act
        CharacterListGameSystem().onResumeGameSystem(characterListActivity, mock())

        // Assert
        verify(preferenceServiceHolder.preferenceService)?.getInt(PreferenceService.GAME_SYSTEM_TYPE)
    }

    @Test
    fun onResumeGameSystem_gameNotLoaded_loadGameSystem() {
        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.isLoaded).doReturn(false)
        gameSystemHolder.gameSystem = gameSystem

        val preferenceService: PreferenceService = mock()
        whenever(preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE)).doReturn(0)
        whenever(preferenceServiceHolder.preferenceService).doReturn(preferenceService)

        val characterListActivity: CharacterListActivity = mock()

        // Act
        CharacterListGameSystem().onResumeGameSystem(characterListActivity, mock())

        // Assert
        verify(preferenceServiceHolder.preferenceService)?.getInt(PreferenceService.GAME_SYSTEM_TYPE)
    }

    @Test
    fun onResumeGameSystem_gameSystemExists_resumeLayout() {
        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.isLoaded).doReturn(true)
        gameSystemHolder.gameSystem = gameSystem
        val characterListLayout: CharacterListLayout = mock()

        // Act
        CharacterListGameSystem().onResumeGameSystem(mock(), characterListLayout)

        // Assert
        verify(characterListLayout).resumeLayout()
    }

    @Test
    fun setUserProperties_gameSystemLoaded_setsUserProperties() {
        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.name).doReturn("myGameSystem")
        whenever(gameSystem.allCharacters).doReturn(listOf(Character(), Character()))
        gameSystemHolder.gameSystem = gameSystem

        // Act
        CharacterListGameSystem().setUserProperties()

        // Assert
        verify(firebaseAnalytics).setUserProperty("game_system", "myGameSystem")
        verify(firebaseAnalytics).setUserProperty("character_count", "2")
    }

    @Test
    fun onDestroy_destroyCalled_destroyResources() {
        // Arrange
        gameSystemHolder.gameSystem = mock()
        gameSystemHolder.dndDbHelper = mock()
        gameSystemHolder.pathfinderDbHelper = mock()

        // Act
        CharacterListGameSystem().onDestroy()

        // Assert
        assertThat(gameSystemHolder.gameSystem).isNull()
        verify(gameSystemHolder.dndDbHelper)?.close()
        verify(gameSystemHolder.pathfinderDbHelper)?.close()
    }

}