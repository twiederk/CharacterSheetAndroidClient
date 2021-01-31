package com.android.ash.charactersheet.gui.main.characterlist

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class GameSystemSelectorRobotronicTest : KoinTest {

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
    fun isShowGameSystemSelector_dndv35DatabaseIsCreated_showGameSystemSelector() {
        // arrange
        val dndDbHelper: DBHelper = mock()
        whenever(dndDbHelper.isCreate).thenReturn(true)
        gameSystemHolder.dndDbHelper = dndDbHelper

        // act
        val showGameSystemSelector = GameSystemSelector(mock()).isShowGameSystemSelector()

        // assert
        assertThat(showGameSystemSelector).isTrue
    }

    @Test
    fun isShowGameSystemSelector_dndv35DatabaseIsCreatedAndGameSystemAlreadySelected_showGameSystemSelector() {
        // arrange
        val dndDbHelper: DBHelper = mock()
        whenever(dndDbHelper.isCreate).thenReturn(true)
        gameSystemHolder.dndDbHelper = dndDbHelper
        val underTest = GameSystemSelector(mock())
        underTest.gameSystemSelected = true

        // act
        val showGameSystemSelector = underTest.isShowGameSystemSelector()

        // assert
        assertThat(showGameSystemSelector).isFalse
    }

    @Test
    fun isShowGameSystemSelector_dndv35DatabaseIsNotCreated_hideGameSystemSelector() {
        // arrange
        val dndDbHelper: DBHelper = mock()
        whenever(dndDbHelper.isCreate).thenReturn(false)
        gameSystemHolder.dndDbHelper = dndDbHelper

        // act
        val showGameSystemSelector = GameSystemSelector(mock()).isShowGameSystemSelector()

        // assert
        assertThat(showGameSystemSelector).isFalse
    }

    @Test
    fun switchGameSystem_switchFromDnDv35ToDnDv35_hideGameSystemSelectorSetPreferencesNoSwitchNecessary() {
        // arrange
        val underTest = GameSystemSelector(mock())
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35
        preferenceServiceHolder.preferenceService = mock()

        // act
        underTest.switchGameSystem(GameSystemType.DNDV35)

        // assert
        assertThat(underTest.gameSystemSelected).isTrue
        verify(preferenceServiceHolder.preferenceService)?.setInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.DNDV35.ordinal)
        argumentCaptor<Bundle> {
            verify(firebaseAnalytics).logEvent(eq("game_system_select"), capture())
            assertThat(firstValue.getString("game_system_name")).isEqualTo(GameSystemType.DNDV35.getName())
        }

    }

}