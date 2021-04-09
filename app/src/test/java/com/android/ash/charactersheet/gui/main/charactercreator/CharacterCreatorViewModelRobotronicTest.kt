package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock

class CharacterCreatorViewModelRobotronicTest : KoinTest {

    private val firebaseAnalytics: FirebaseAnalytics by inject()
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
    fun onRollDice_rollDiceIsLoggedInFirebase() {

        // arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allRaces).thenReturn(listOf(Race().apply { name = "myRace" }))
        whenever(gameSystem.allCharacterClasses).thenReturn(listOf(CharacterClass().apply { name = "myClass" }))
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(any())).thenReturn("Male")
        whenever(displayService.getDisplayAlignment(any())).thenReturn("Lawful Good")
        whenever(gameSystem.displayService).thenReturn(displayService)
        gameSystemHolder.gameSystem = gameSystem

        // act
        CharacterCreatorViewModel().onRollDice()

        // assert
        verify(firebaseAnalytics).logEvent(FBAnalytics.Event.STANDARD_METHOD_DICE_ROLL, null)
    }

}