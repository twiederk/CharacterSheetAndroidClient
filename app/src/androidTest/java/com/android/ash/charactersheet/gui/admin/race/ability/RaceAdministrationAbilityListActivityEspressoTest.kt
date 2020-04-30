package com.android.ash.charactersheet.gui.admin.race.ability

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
import com.d20charactersheet.framework.boc.model.Ability
import com.d20charactersheet.framework.boc.service.AbilityService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import java.io.Serializable

class RaceAdministrationAbilityListActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<RaceAdministrationAbilityListActivity>

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
        whenever(abilityService.getAbilityById(1, allAbilities)).doReturn(ability)
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.abilityService).doReturn(abilityService)
        whenever(gameSystem.allAbilities).doReturn(allAbilities)

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, RaceAdministrationAbilityListActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, listOf(1) as Serializable)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(Is.`is`("Race Abilities"))))

        onView(withId(R.id.name))
                .check(matches(withText("MyAbility")))
                .check(matches(isDisplayed()))
    }

}