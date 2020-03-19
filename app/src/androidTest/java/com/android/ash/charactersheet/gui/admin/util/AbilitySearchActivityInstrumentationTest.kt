package com.android.ash.charactersheet.gui.admin.util

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Ability
import com.d20charactersheet.framework.boc.service.AbilityService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.SimpleDisplayService
import com.d20charactersheet.framework.boc.service.SpelllistService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class AbilitySearchActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<AbilitySearchActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {
        // Arrange
        val ability = Ability()
        ability.name = "MyAbility"
        val allAbilities = listOf(ability)
        val abilityService: AbilityService = mock()
        whenever(abilityService.getAllAbilities(any(), any(), any())).doReturn(allAbilities)
        val spelllistService: SpelllistService = mock()
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(SimpleDisplayService())
        whenever(gameSystem.abilityService).doReturn(abilityService)
        whenever(gameSystem.spelllistService).doReturn(spelllistService)
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(AbilitySearchActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Search Ability"))))
        onView(withId(R.id.name)).check(matches(withText("MyAbility"))).check(matches(isDisplayed()))
    }

}