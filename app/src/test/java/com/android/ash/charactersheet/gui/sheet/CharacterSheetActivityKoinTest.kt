package com.android.ash.charactersheet.gui.sheet

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.dsl.createCharacter
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
        val character = createCharacter {
            race {
                racename = "myRace"
            }
            classLevels {
                classLevel { classname = "myFirstClass"; level = 1 }
                classLevel { classname = "mySecondClass"; level = 2 }
            }
        }

        // Act
        CharacterSheetActivity().logEventCharacter(character)

        // Assert
        val captor = argumentCaptor<Bundle>()
        verify(firebaseAnalytics).logEvent(eq("character_open"), captor.capture())
        assertThat(captor.firstValue.getString("race_name")).isEqualTo("myRace")
        assertThat(captor.firstValue.getString("class_levels")).isEqualTo("myFirstClass (1);mySecondClass (2);")
    }

}
