package com.android.ash.charactersheet.gui.main

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.ClassLevel
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
class CharacterCreateActivityRobotronicTest : KoinTest {

    private val firebaseAnalytics: Lazy<FirebaseAnalytics> = inject()

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
    fun logEventCharacterCreate_createCharacter_logsCharacterCreateEvent() {
        // Arrange
        val character = Character().apply {
            race = Race().apply { name = "myRace" }
            classLevels = listOf(ClassLevel(CharacterClass().apply { name = "myClass"; classAbilities = listOf() }))
        }

        // Act
        CharacterCreateActivity().logEventCharacterCreate(character)

        // Assert
        argumentCaptor<Bundle> {
            verify(firebaseAnalytics.value).logEvent(eq("character_create"), capture())
            assertThat(firstValue.getString("race_name")).isEqualTo("myRace")
            assertThat(firstValue.getString("class_name")).isEqualTo("myClass")
        }
    }

}