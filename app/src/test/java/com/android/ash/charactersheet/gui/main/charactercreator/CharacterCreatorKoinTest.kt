package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito

class CharacterCreatorKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

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
        declareMock<CharacterHolder>()
        declareMock<FirebaseAnalytics>()
    }

    @KoinApiExtension
    @Test
    fun createCharacter_everythingIsFine_createNewCharacter() {
        // arrange
        val characterService: CharacterService = mock()
        val gameSystem: GameSystem = mock()

        val myRace = Race().apply { name = "myRace" }
        val allRaces = listOf(myRace)
        whenever(gameSystem.allRaces).thenReturn(allRaces)
        val raceService: RaceService = mock()
        whenever(raceService.findRaceByName(myRace.name, allRaces)).thenReturn(myRace)
        whenever(gameSystem.raceService).thenReturn(raceService)

        val myClass = CharacterClass().apply { name = "myClass" }
        val allClasses = listOf(myClass)
        whenever(gameSystem.allCharacterClasses).thenReturn(allClasses)
        val characterClassService: CharacterClassService = mock()
        whenever(characterClassService.findClassByName(myClass.name, allClasses)).thenReturn(myClass)
        whenever(gameSystem.characterClassService).thenReturn(characterClassService)

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(any())).thenReturn("Male")
        whenever(displayService.getDisplayAlignment(any())).thenReturn("Lawful Good")
        whenever(gameSystem.displayService).thenReturn(displayService)


        whenever(gameSystem.characterService).thenReturn(characterService)
        whenever(gameSystem.allXpTables).thenReturn(listOf(XpTable().apply { name = "myXpTable" }))

        gameSystemHolder.gameSystem = gameSystem

        val characterCreatorViewModel = CharacterCreatorViewModel().apply {
            name = "myName"
            player = "myPlayer"
            race = Race().apply { name = "myRace" }
            this.clazz = "myClass"
            gender = "Male"
            alignment = "Lawful Good"
        }

        // act
        val character = CharacterCreator().createCharacter(characterCreatorViewModel)

        // assert
        assertThat(character).isNotNull
        assertThat(character.name).isEqualTo("myName")
        assertThat(character.player).isEqualTo("myPlayer")
        assertThat(character.race.name).isEqualTo("myRace")
        assertThat(character.classLevels[0].characterClass.name).isEqualTo("myClass")
        assertThat(character.classLevels[0].level).isEqualTo(1)
        assertThat(character.sex).isEqualTo(Sex.MALE)
        assertThat(character.alignment).isEqualTo(Alignment.LAWFUL_GOOD)
        assertThat(character.xpTable.name).isEqualTo("myXpTable")

        assertThat(character.strength).isEqualTo(10)
        assertThat(character.dexterity).isEqualTo(10)
        assertThat(character.constitution).isEqualTo(10)
        assertThat(character.intelligence).isEqualTo(10)
        assertThat(character.wisdom).isEqualTo(10)
        assertThat(character.charisma).isEqualTo(10)
        assertThat(character.imageId).isEqualTo(0)
        assertThat(character.thumbImageId).isEqualTo(1)

        verify(characterService).createCharacter(eq(character), any())
        verify(characterHolder).character = character
    }

}