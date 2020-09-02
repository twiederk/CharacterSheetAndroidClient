package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Ability
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterClass.AnyCharacterClass.ANY_CHARACTER_CLASS
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Size
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.not
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class RaceAbilityPageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    @Test
    fun onCreateView() {
        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySize(any())).doReturn("mySize")

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)

        gameSystemHolder.gameSystem = gameSystem
        characterHolder.character = Character().apply {
            race = Race().apply {
                name = "myRace"
                favoredCharacterClass = ANY_CHARACTER_CLASS
                size = Size.MEDIUM
                baseLandSpeed = 20
                abilities = listOf(Ability().apply { name = "myAbility" })
            }
        }

        // Act
        launchFragmentInContainer<RaceAbilityPageFragment>()

        // Assert
        onView(withId(R.id.race_name)).check(matches(withText("Name: myRace")))
        onView(withId(R.id.race_favorite_character_class_name)).check(matches(withText("Class: Any")))
        onView(withId(R.id.race_size)).check(matches(withText("Size: mySize")))
        onView(withId(R.id.race_reach)).check(matches(withText("Reach: 5")))
        onView(withId(R.id.race_speed)).check(matches(withText("Speed: 20")))
        onView(withId(R.id.race_space)).check(matches(withText("Space: 5.0")))
        onView(withId(R.id.ability_name)).check(matches(withText("myAbility")))
        onView(withId(R.id.ability_type)).check(matches(not(isDisplayed())))
        onView(withId(R.id.ability_description)).check(matches(not(isDisplayed())))
    }

}