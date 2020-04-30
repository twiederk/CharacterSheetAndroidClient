package com.android.ash.charactersheet.gui.admin.skill

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Attribute
import com.d20charactersheet.framework.boc.model.Skill
import com.d20charactersheet.framework.boc.service.GameSystem

import com.d20charactersheet.framework.boc.service.SkillService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.isEmptyString
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class SkillAdministrationCreateActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SkillAdministrationCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val skillService: SkillService = mock()
        whenever(skillService.getSkillDescription(any())).doReturn(Skill().apply { attribute = Attribute.STRENGTH })
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.skillService).doReturn(skillService)
        whenever(gameSystem.spelllistService).doReturn(mock())
        whenever(gameSystem.abilityService).doReturn(mock())
        whenever(gameSystem.characterClassService).doReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SkillAdministrationCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Skill"))))
        onView(withId(R.id.skill_administration_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.skill_administration_attribute)).check(matches(withSpinnerText("STRENGTH")))
        onView(withId(R.id.skill_administration_untrained)).check(matches(isNotChecked()))
        onView(withId(R.id.skill_administration_description)).check(matches(withText(isEmptyString())))
    }

}