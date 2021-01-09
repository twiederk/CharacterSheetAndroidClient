package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class CharacterCreatorRobotronicTest : KoinTest {

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
    fun createCharacter_everythingIsFine_createCharacterEventIsLoggedInFirebase() {
        // arrange
        val characterData = CharacterData().apply {
            race = Race().apply { name = "myRace" }
            clazz = CharacterClass().apply {
                name = "myClass"
                classAbilities = listOf()
            }
        }

        // act
        CharacterCreator(characterData).createCharacter()

        // Assert
        argumentCaptor<Bundle> {
            verify(firebaseAnalytics).logEvent(eq("character_create"), capture())
            assertThat(firstValue.getString("race_name")).isEqualTo("myRace")
            assertThat(firstValue.getString("class_name")).isEqualTo("myClass")
        }

    }

}