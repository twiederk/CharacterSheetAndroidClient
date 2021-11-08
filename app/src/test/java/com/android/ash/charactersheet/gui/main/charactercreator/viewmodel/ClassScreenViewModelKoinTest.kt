package com.android.ash.charactersheet.gui.main.charactercreator.viewmodel

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.d20charactersheet.framework.boc.model.CharacterClass
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
        gameSystemHolder.gameSystem = gameSystem
        gameSystemHolder.gameSystemType = GameSystemType.DND5E
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
        assertThat(classScreenViewModel.clazz.name).isEqualTo("anotherClass")
        assertThat(classScreenViewModel.classList).containsExactly(
            CharacterClass().apply { name = "anotherClass" },
            CharacterClass().apply { name = "myClass" }
        )
        assertThat(classScreenViewModel.gameSystemType).isEqualTo(GameSystemType.DND5E)
    }

    @Test
    fun reset_resetProperties_allPropertiesAreReset() {
        // arrange
        val underTest = ClassScreenViewModel(gameSystemHolder)
        underTest.classList =
            listOf(
                CharacterClass().apply { name = "firstClass" },
                CharacterClass().apply { name = "secondClass" }
            )
        underTest.clazz = underTest.classList[1]
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        // act
        underTest.reset()

        // assert
        assertThat(underTest.clazz.name).isEqualTo("anotherClass")
        assertThat(underTest.gameSystemType).isEqualTo(GameSystemType.DNDV35)
    }


}