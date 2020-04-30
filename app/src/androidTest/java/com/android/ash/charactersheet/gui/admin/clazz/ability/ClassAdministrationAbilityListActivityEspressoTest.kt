package com.android.ash.charactersheet.gui.admin.clazz.ability

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
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ClassAdministrationAbilityListActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<ClassAdministrationAbilityListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val ability = Ability()
        ability.id = 1
        ability.name = "myAbility"

        val abilityService: AbilityService = mock()
        whenever(abilityService.getAbilityById(any(), any())).doReturn(ability)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.abilityService).doReturn(abilityService)
        whenever(gameSystem.allAbilities).doReturn(listOf(ability))

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, ClassAdministrationAbilityListActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, intArrayOf(1, 2))
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Class Abilities"))))
        onView(withId(R.id.name)).check(matches(withText("myAbility (2)")))
    }

}