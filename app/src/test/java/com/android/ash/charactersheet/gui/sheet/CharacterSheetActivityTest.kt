package com.android.ash.charactersheet.gui.sheet

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
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
class CharacterSheetActivityTest : KoinTest {

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
    fun logEventCharacter_character_logsCharacterRaceAndClassLevels() {
        // Arrange
        val character = Character().apply {
            race = Race().apply { name = "myRace" }
            classLevels = listOf(
                    ClassLevel(CharacterClass().apply { name = "myFirstClass"; classAbilities = listOf() }, 1),
                    ClassLevel(CharacterClass().apply { name = "mySecondClass"; classAbilities = listOf() }, 2))
        }

        // Act
        CharacterSheetActivity().logEventCharacter(character)

        // Assert
        argumentCaptor<Bundle> {
            verify(firebaseAnalytics.value).logEvent(eq("character_open"), capture())
            assertThat(firstValue.getString("race_name")).isEqualTo("myRace")
            assertThat(firstValue.getString("class_levels")).isEqualTo("myFirstClass (1);mySecondClass (2);")
        }
    }

}
