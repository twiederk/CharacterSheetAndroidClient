package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.XpTable
import com.d20charactersheet.framework.boc.service.CharacterClassService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RaceService
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.robolectric.annotation.Config

@KoinApiExtension
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class CharacterCreatorRobotronicTest : KoinTest {

    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val characterCreatorViewModel: CharacterCreatorViewModel by inject()
    private val gameSystemHolder: GameSystemHolder by inject()

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
    fun createCharacter_everythingIsFine_createCharacterEventIsLoggedInFirebase() {
        // arrange
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
        whenever(gameSystem.allXpTables).thenReturn(listOf(XpTable().apply { name = "myXpTable" }))
        gameSystemHolder.gameSystem = gameSystem

        characterCreatorViewModel.race = Race().apply { name = "myRace" }
        characterCreatorViewModel.clazz = "myClass"
        characterCreatorViewModel.gender = "Male"
        characterCreatorViewModel.alignment = "Lawful Good"

        // act
        CharacterCreator().createCharacter(characterCreatorViewModel)

        // Assert
        argumentCaptor<Bundle> {
            verify(firebaseAnalytics).logEvent(eq("character_create"), capture())
            assertThat(firstValue.getString("race_name")).isEqualTo("myRace")
            assertThat(firstValue.getString("class_name")).isEqualTo("myClass")
        }

    }

}