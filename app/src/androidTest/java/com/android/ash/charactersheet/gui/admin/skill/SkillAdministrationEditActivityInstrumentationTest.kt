package com.android.ash.charactersheet.gui.admin.skill

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
import com.d20charactersheet.framework.boc.model.Attribute
import com.d20charactersheet.framework.boc.model.Skill
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.SimpleDisplayService
import com.d20charactersheet.framework.boc.service.SkillService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class SkillAdministrationEditActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SkillAdministrationEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val skill = Skill()
        skill.id = 1
        skill.name = "mySkill"
        skill.attribute = Attribute.STRENGTH
        skill.description = "myDescription"

        val skillService: SkillService = mock()
        whenever(skillService.getSkillById(any(), any())).doReturn(skill)
        whenever(skillService.getSkillDescription(any())).doReturn(skill)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(SimpleDisplayService())
        whenever(gameSystem.skillService).doReturn(skillService)
        whenever(gameSystem.spelllistService).doReturn(mock())
        whenever(gameSystem.abilityService).doReturn(mock())
        whenever(gameSystem.characterClassService).doReturn(mock())

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, SkillAdministrationEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Skill"))))
        onView(withId(R.id.skill_administration_name)).check(matches(withText("mySkill")))
        onView(withId(R.id.skill_administration_attribute)).check(matches(withSpinnerText("STRENGTH")))
        onView(withId(R.id.skill_administration_untrained)).check(matches(isNotChecked()))
        onView(withId(R.id.skill_administration_description)).check(matches(withText("myDescription")))
    }

}