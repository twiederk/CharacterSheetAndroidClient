package com.android.ash.charactersheet.gui.admin.clazz.skill

import android.content.Context
import android.content.Intent
import android.widget.CheckBox
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.Constants
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Skill
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.SkillService
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CharacterClassSkillActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<CharacterClassSkillActivity>

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


        val allSkills = listOf(skill)

        val skillService: SkillService = mock()
        whenever(skillService.getSkillById(any(), any())).doReturn(skill)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.skillService).doReturn(skillService)
        whenever(gameSystem.allSkills).doReturn(allSkills)
        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, CharacterClassSkillActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, intArrayOf(1))
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Class Skills"))))
        onView(allOf(isAssignableFrom(CheckBox::class.java), isChecked())).check(matches(withText("mySkill")))
    }

}