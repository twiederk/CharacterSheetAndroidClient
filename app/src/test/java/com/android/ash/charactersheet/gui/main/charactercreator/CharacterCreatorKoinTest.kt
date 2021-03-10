package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.CharacterService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock

class CharacterCreatorKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<GameSystemHolder>()
        declareMock<CharacterHolder>()
        declareMock<FirebaseAnalytics>()
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun createCharacter_everythingIsFine_createNewCharacter() {
        // arrange
        val characterCreatorViewModel = CharacterCreatorViewModel().apply {
            name = "myName"
            player = "myPlayer"
            race = Race().apply { name = "myRace" }
            clazz = CharacterClass().apply {
                name = "myClass"
                classAbilities = listOf()
            }
        }

        val characterService: CharacterService = mock()
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.characterService).thenReturn(characterService)
        whenever(gameSystem.allXpTables).thenReturn(listOf(XpTable().apply { name = "myXpTable" }))
        whenever(gameSystemHolder.gameSystem).thenReturn(gameSystem)

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