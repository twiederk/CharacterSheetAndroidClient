package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.CharacterClass
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

class ClassScreenViewModelKoinTest : KoinTest {

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
        whenever(gameSystem.allCharacterClasses).thenReturn(
            mutableListOf(
                CharacterClass().apply { name = "myClass" },
                CharacterClass().apply { name = "anotherClass" })
        )

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
        val classScreenViewModel = ClassScreenViewModel(gameSystemHolder)

        // assert
        assertThat(classScreenViewModel.name.value).isEqualTo("")
        assertThat(classScreenViewModel.player.value).isEqualTo("")
        assertThat(classScreenViewModel.clazz.value.name).isEqualTo("anotherClass")
        assertThat(classScreenViewModel.classList.value).containsExactly(
            CharacterClass().apply { name = "anotherClass" },
            CharacterClass().apply { name = "myClass" }
        )
        assertThat(classScreenViewModel.gender.value).isEqualTo("Male")
        assertThat(classScreenViewModel.alignment.value).isEqualTo("Lawful Good")
    }

    @Test
    fun reset_resetProperties_allPropertiesAreReset() {
        // arrange
        val underTest = ClassScreenViewModel(gameSystemHolder)
        underTest.classList.value =
            listOf(
                CharacterClass().apply { name = "firstClass" },
                CharacterClass().apply { name = "secondClass" }
            )
        underTest.name.value = "myName"
        underTest.player.value = "myPlayer"
        underTest.clazz.value = underTest.classList.value[1]
        underTest.gender.value = "Female"
        underTest.alignment.value = "Neutral"

        // act
        underTest.reset()

        // assert
        assertThat(underTest.name.value).isEqualTo("")
        assertThat(underTest.player.value).isEqualTo("")
        assertThat(underTest.clazz.value.name).isEqualTo("anotherClass")
        assertThat(underTest.gender.value).isEqualTo("Male")
        assertThat(underTest.alignment.value).isEqualTo("Lawful Good")
    }


}