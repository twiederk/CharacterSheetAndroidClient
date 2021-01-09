package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.d20charactersheet.framework.boc.model.Die
import com.d20charactersheet.framework.boc.service.CharacterCreatorServiceImpl
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.*

class AbilityScoresFragmentEspressoTest : KoinTest {

    private val characterCreator: CharacterCreator by inject()
    private val gameSystemHolder: GameSystemHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    @After
    fun tearDown() {
        Die.setRandom(Random())

        val characterData = characterCreator.characterData
        characterData.strength = 10
        characterData.dexterity = 10
        characterData.constitution = 10
        characterData.intelligence = 10
        characterData.wisdom = 10
        characterData.charisma = 10
    }

    @Test
    fun fragmentResumed_displayFragment() {
        // arrange
        val scenario = FragmentScenario.launchInContainer(AbilityScoresFragment::class.java)

        // act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // assert
        onView(withId(R.id.ability_scores_roll_dice)).check(matches(withText("Roll Dice (4d6)")))
        onView(withId(R.id.ability_scores_str)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_dex)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_con)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_int)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_wis)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_cha)).check(matches(withText("10")))
    }

    @Test
    fun fillAbilityScores_readDataFromCharacterData_displayProperAbilitySores() {

        // arrange
        val scenario = FragmentScenario.launchInContainer(AbilityScoresFragment::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // act
        scenario.onFragment { fragment -> fragment.fillAbilityScores() }

        // assert
        onView(withId(R.id.ability_scores_str)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_dex)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_con)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_int)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_wis)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_cha)).check(matches(withText("10")))
    }

    @Test
    fun updateCharacterData_readDataFromGuiComponents_characterDataContainsDataFromGuiComponents() {

        // arrange
        val scenario = FragmentScenario.launchInContainer(AbilityScoresFragment::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.ability_scores_str)).perform(replaceText("11"))
        onView(withId(R.id.ability_scores_dex)).perform(replaceText("12"))
        onView(withId(R.id.ability_scores_con)).perform(replaceText("13"))
        onView(withId(R.id.ability_scores_int)).perform(replaceText("14"))
        onView(withId(R.id.ability_scores_wis)).perform(replaceText("15"))
        onView(withId(R.id.ability_scores_cha)).perform(replaceText("16"))

        // act
        scenario.onFragment { fragment -> fragment.updateCharacterData() }

        // assert
        val characterData = characterCreator.characterData
        assertThat(characterData.strength).isEqualTo(11)
        assertThat(characterData.dexterity).isEqualTo(12)
        assertThat(characterData.constitution).isEqualTo(13)
        assertThat(characterData.intelligence).isEqualTo(14)
        assertThat(characterData.wisdom).isEqualTo(15)
        assertThat(characterData.charisma).isEqualTo(16)
    }

    @Test
    fun rollDice_newRandomAbilityScores_characterDataAndGuiHoldNewAbilityScores() {

        // arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.characterCreatorService).doReturn(CharacterCreatorServiceImpl())
        gameSystemHolder.gameSystem = gameSystem

        Die.setRandom(Random(1))

        val scenario = FragmentScenario.launchInContainer(AbilityScoresFragment::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // act
        onView(withId(R.id.ability_scores_roll_dice)).perform(click())

        // assert
        onView(withId(R.id.ability_scores_str)).check(matches(withText("13")))
        onView(withId(R.id.ability_scores_dex)).check(matches(withText("13")))
        onView(withId(R.id.ability_scores_con)).check(matches(withText("12")))
        onView(withId(R.id.ability_scores_int)).check(matches(withText("11")))
        onView(withId(R.id.ability_scores_wis)).check(matches(withText("10")))
        onView(withId(R.id.ability_scores_cha)).check(matches(withText("14")))

        val characterData = characterCreator.characterData
        assertThat(characterData.strength).isEqualTo(13)
        assertThat(characterData.dexterity).isEqualTo(13)
        assertThat(characterData.constitution).isEqualTo(12)
        assertThat(characterData.intelligence).isEqualTo(11)
        assertThat(characterData.wisdom).isEqualTo(10)
        assertThat(characterData.charisma).isEqualTo(14)

    }

}