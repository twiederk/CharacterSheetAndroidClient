package com.android.ash.charactersheet.gui.sheet

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.ClassLevel
import com.d20charactersheet.framework.boc.model.Race
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class CharacterSheetActivityKoinTest : KoinTest {

    private val firebaseAnalytics: FirebaseAnalytics by inject()

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
        declareMock<FirebaseAnalytics>()
    }

    @Test
    fun logEventCharacter_character_logsCharacterClassLevels() {
        // Arrange
        val character = Character().apply {
            race = Race().apply { name = "myRace" }
            classLevels = listOf(
                ClassLevel(CharacterClass().apply { name = "myFirstClass"; classAbilities = listOf() }, 1),
                ClassLevel(CharacterClass().apply { name = "mySecondClass"; classAbilities = listOf() }, 2)
            )
        }

        // Act
        CharacterSheetActivity().logEventCharacter(character)

        // Assert
        argumentCaptor<Bundle> {
            verify(firebaseAnalytics).logEvent(eq("character_open"), capture())
            assertThat(firstValue.getString("race_name")).isEqualTo("myRace")
            assertThat(firstValue.getString("class_levels")).isEqualTo("myFirstClass (1);mySecondClass (2);")
        }
    }

}
