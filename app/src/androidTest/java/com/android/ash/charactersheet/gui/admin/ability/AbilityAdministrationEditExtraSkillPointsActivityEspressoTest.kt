package com.android.ash.charactersheet.gui.admin.ability

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.Constants
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.AbilityType
import com.d20charactersheet.framework.boc.model.ExtraSkillPointsAbility
import com.d20charactersheet.framework.boc.service.AbilityService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class AbilityAdministrationEditExtraSkillPointsActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<AbilityAdministrationEditExtraSkillPointsActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val ability = ExtraSkillPointsAbility()
        ability.name = "myAbility"
        ability.abilityType = AbilityType.EXTRAORDINARY
        ability.skillPoints = 1
        ability.description = "myDescription"

        val abilityService: AbilityService = mock()
        whenever(abilityService.getAbilityById(any(), any())).doReturn(ability)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.abilityService).doReturn(abilityService)

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, AbilityAdministrationEditExtraSkillPointsActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Extra Skill Points Ability"))))
        onView(withId(R.id.ability_administration_name)).check(matches(withText("myAbility")))
        onView(withId(R.id.ability_administration_type)).check(matches(withSpinnerText("EXTRAORDINARY")))
        onView(allOf(withParent(withId(R.id.ability_administration_extraskillpoints)), withClassName(endsWith("TextView")))).check(matches(withText("1")))
        onView(withId(R.id.ability_administration_desc)).check(matches(withText("myDescription")))
    }

}