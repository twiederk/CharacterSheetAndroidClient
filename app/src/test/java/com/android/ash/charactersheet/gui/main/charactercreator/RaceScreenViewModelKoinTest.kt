package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.service.AndroidImageService
import com.d20charactersheet.framework.boc.model.Race
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

class RaceScreenViewModelKoinTest : KoinTest {

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

        whenever(gameSystem.allRaces).thenReturn(
            mutableListOf(
                Race().apply { name = "myRace" },
                Race().apply { name = "anotherRace" })
        )

        gameSystemHolder.gameSystem = gameSystem
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun constructor_initCharacter_characterWithDefaultValue() {

        // act
        val raceScreenViewModel = RaceScreenViewModel(gameSystemHolder)

        // assert
        assertThat(raceScreenViewModel.race.value.name).isEqualTo("anotherRace")
        assertThat(raceScreenViewModel.raceList.value).containsExactly(
            Race().apply { name = "anotherRace" },
            Race().apply { name = "myRace" }
        )

    }

    @Test
    fun getBitmap_bitmapExists_returnBitmap() {
        // arrange
        val imageService: AndroidImageService = mock()
        whenever(imageService.getBitmap(any())).thenReturn(mock())
        whenever(gameSystemHolder.gameSystem?.imageService).thenReturn(imageService)

        val underTest = RaceScreenViewModel(gameSystemHolder)

        // act
        val bitmap = underTest.getBitmap(1)

        // assert
        assertThat(bitmap).isNotNull
    }

    @Test
    fun reset_resetProperties_allPropertiesAreReset() {
        // arrange
        val underTest = RaceScreenViewModel(gameSystemHolder)
        underTest.raceList.value =
            listOf(Race().apply { name = "firstRace" }, Race().apply { name = "secondRace" })
        underTest.race.value = underTest.raceList.value[1]

        // act
        underTest.reset()

        // assert
        assertThat(underTest.race.value.name).isEqualTo("anotherRace")


    }
}