package com.android.ash.charactersheet.gui.admin.skill

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Attribute
import com.d20charactersheet.framework.boc.model.Skill
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.SkillService
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SkillAdministrationListActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SkillAdministrationListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allSkills).doReturn(listOf(Skill().apply { id = 1; name = "mySkill" }))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SkillAdministrationListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Skill Administration"))))
        onView(withId(R.id.name)).check(matches(withText("mySkill"))).check(matches(isDisplayed()))
    }

    @Test
    fun fab_onClick_displaySkillAdministrationCreateActivity() {

        // Arrange
        val skillService: SkillService = mock()
        whenever(skillService.getSkillDescription(any())).doReturn(Skill().apply { attribute = Attribute.STRENGTH })

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allSkills).doReturn(listOf(Skill().apply { id = 1; name = "mySkill" }))
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.skillService).doReturn(skillService)
        whenever(gameSystem.spelllistService).doReturn(mock())
        whenever(gameSystem.abilityService).doReturn(mock())
        whenever(gameSystem.characterClassService).doReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SkillAdministrationListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Skill"))))
    }

}