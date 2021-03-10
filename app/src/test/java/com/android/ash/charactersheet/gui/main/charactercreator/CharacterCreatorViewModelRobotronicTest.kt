package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.appModule
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.verify
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

        // act
        CharacterCreatorViewModel().onRollDice()

        // assert
        verify(firebaseAnalytics).logEvent(FBAnalytics.Event.STANDARD_METHOD_DICE_ROLL, null)
    }

}