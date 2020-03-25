package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.*
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.Matchers.not
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ClassAbilityPageFragmentInstrumentationTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        gameSystemHolder.gameSystem = mock()
        characterHolder.character = Character().apply {
            classLevels = listOf(ClassLevel(
                    CharacterClass().apply {
                        classAbilities = listOf(ClassAbility(
                                Ability().apply { name = "myAbility" }))
                    }, 1))
        }

        // Act
        launchFragmentInContainer<ClassAbilityPageFragment>()

        // Assert
        onView(withId(R.id.ability_name)).check(matches(withText("myAbility")))
        onView(withId(R.id.ability_owned)).check(matches(not(isDisplayed())))
        onView(withId(R.id.ability_type)).check(matches(not(isDisplayed())))
        onView(withId(R.id.ability_character_class)).check(matches(not(isDisplayed())))
        onView(withId(R.id.ability_level)).check(matches(not(isDisplayed())))
        onView(withId(R.id.ability_description)).check(matches(not(isDisplayed())))
    }

}