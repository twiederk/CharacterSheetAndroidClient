package com.android.ash.charactersheet.gui.sheet.skill

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.*
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterSkill
import com.d20charactersheet.framework.boc.model.Skill
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SkillEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()
    private val preferenceServiceHolder by inject<PreferenceServiceHolder>()

    private lateinit var scenario: ActivityScenario<SkillEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.ruleService).doReturn(mock())
        whenever(gameSystem.characterService).doReturn(mock())

        gameSystemHolder.gameSystem = gameSystem

        preferenceServiceHolder.preferenceService = mock()

        characterHolder.character = Character().apply {
            name = "myCharacter"
            characterSkills = listOf(
                    CharacterSkill(Skill().apply { name = "mySkill" }).apply { rank = 1.5F; modifier = 3 })
        }

        scenario = ActivityScenario.launch(SkillEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myCharacter"))))
        onView(withId(R.id.skill_name)).check(matches(withText("mySkill")))
        onView(allOf(withParent(withId(R.id.skill_rank)), withClassName(endsWith("TextView")))).check(matches(withText("1.5")))
        onView(allOf(withParent(withId(R.id.skill_modifier)), withClassName(endsWith("TextView")))).check(matches(withText("3")))
    }

}