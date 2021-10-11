package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.service.CharacterClassService
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AbilityScoresScreenViewModelRobotronicTest : KoinTest {

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
        whenever(gameSystem.allCharacterClasses).thenReturn(listOf(CharacterClass().apply {
            name = "myClass"
        }))

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(any())).thenReturn("Male")
        whenever(displayService.getDisplayAlignment(any())).thenReturn("Lawful Good")
        whenever(gameSystem.displayService).thenReturn(displayService)

        val characterClassService: CharacterClassService = mock()
        whenever(characterClassService.getStarterPack(any(), any())).thenReturn(mock())
        whenever(gameSystem.characterClassService).thenReturn(characterClassService)

        whenever(gameSystem.characterCreatorService).thenReturn(mock())

        whenever(gameSystem.ruleService).thenReturn(mock())

        whenever(gameSystem.itemService).thenReturn(mock())

        gameSystemHolder.gameSystem = gameSystem

        // act
        AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics).onRollDice()

        // assert
        verify(firebaseAnalytics).logEvent(FBAnalytics.Event.STANDARD_METHOD_DICE_ROLL, null)
    }

}