package com.android.ash.charactersheet.gui.main.characterlist

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class GameSystemSelectorRobotronicTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<FirebaseAnalytics>()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun isShowGameSystemSelector_dnd5eDatabaseIsCreated_showGameSystemSelector() {
        // arrange
        val dnd5eDbHelper: DBHelper = mock()
        whenever(dnd5eDbHelper.isCreate).thenReturn(true)
        gameSystemHolder.dnd5eDbHelper = dnd5eDbHelper

        // act
        val showGameSystemSelector = GameSystemSelector(mock()).isShowGameSystemSelector()

        // assert
        assertThat(showGameSystemSelector).isTrue
    }

    @Test
    fun isShowGameSystemSelector_dnd5eDatabaseIsCreatedAndGameSystemAlreadySelected_showGameSystemSelector() {
        // arrange
        val dnd5eDbHelper: DBHelper = mock()
        whenever(dnd5eDbHelper.isCreate).thenReturn(true)
        gameSystemHolder.dnd5eDbHelper = dnd5eDbHelper
        val underTest = GameSystemSelector(mock())
        underTest.gameSystemSelected = true

        // act
        val showGameSystemSelector = underTest.isShowGameSystemSelector()

        // assert
        assertThat(showGameSystemSelector).isFalse
    }

    @Test
    fun isShowGameSystemSelector_dnd5eDatabaseIsNotCreated_hideGameSystemSelector() {
        // arrange
        val dnd5eDbHelper: DBHelper = mock()
        whenever(dnd5eDbHelper.isCreate).thenReturn(false)
        gameSystemHolder.dnd5eDbHelper = dnd5eDbHelper

        // act
        val showGameSystemSelector = GameSystemSelector(mock()).isShowGameSystemSelector()

        // assert
        assertThat(showGameSystemSelector).isFalse
    }

    @Test
    fun switchGameSystem_switchFromDnD5eToDnD5e_hideGameSystemSelectorSetPreferencesNoSwitchNecessary() {
        // arrange
        val underTest = GameSystemSelector(mock())
        gameSystemHolder.gameSystemType = GameSystemType.DND5E
        preferenceServiceHolder.preferenceService = mock()

        // act
        underTest.switchGameSystem(GameSystemType.DND5E)

        // assert
        assertThat(underTest.gameSystemSelected).isTrue
        verify(preferenceServiceHolder.preferenceService)?.setInt(
            PreferenceService.GAME_SYSTEM_TYPE,
            GameSystemType.DND5E.ordinal
        )
        verify(firebaseAnalytics).logEvent("game_system_select_dnd5e", null)
    }

}