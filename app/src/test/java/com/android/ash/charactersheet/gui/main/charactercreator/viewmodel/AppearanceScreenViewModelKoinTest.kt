package com.android.ash.charactersheet.gui.main.charactercreator.viewmodel

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AppearanceScreenViewModelKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }

    @Before
    fun before() {
        val gameSystem: GameSystem = mock()
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(any())).thenReturn("Male")
        whenever(displayService.getDisplayAlignment(any())).thenReturn("Lawful Good")

        whenever(gameSystem.displayService).thenReturn(displayService)
        gameSystemHolder.gameSystem = gameSystem
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun constructor_initCharacter_characterWithDefaultValue() {

        // act
        val appearanceScreenViewModel = AppearanceScreenViewModel(gameSystemHolder)

        // assert
        assertThat(appearanceScreenViewModel.name).isEqualTo("")
        assertThat(appearanceScreenViewModel.player).isEqualTo("")
        assertThat(appearanceScreenViewModel.gender).isEqualTo("Male")
        assertThat(appearanceScreenViewModel.alignment).isEqualTo("Lawful Good")
    }

    @Test
    fun reset_resetProperties_allPropertiesAreReset() {
        // arrange
        val underTest = AppearanceScreenViewModel(gameSystemHolder)
        underTest.name = "myName"
        underTest.player = "myPlayer"
        underTest.gender = "Female"
        underTest.alignment = "Neutral"

        // act
        underTest.reset()

        // assert
        assertThat(underTest.name).isEqualTo("")
        assertThat(underTest.player).isEqualTo("")
        assertThat(underTest.gender).isEqualTo("Male")
        assertThat(underTest.alignment).isEqualTo("Lawful Good")
    }


}