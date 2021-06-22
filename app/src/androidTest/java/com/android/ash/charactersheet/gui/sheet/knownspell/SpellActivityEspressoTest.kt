package com.android.ash.charactersheet.gui.sheet.knownspell

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
import com.d20charactersheet.framework.boc.model.CastingTime
import com.d20charactersheet.framework.boc.model.Range
import com.d20charactersheet.framework.boc.model.Spell
import com.d20charactersheet.framework.boc.model.SpellResistance
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.SpelllistService
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SpellActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SpellActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val spell = Spell().apply {
            name = "mySpell"
            castingTime = CastingTime.NONE
            range = Range.CLOSE
            effect = "myEffect"
            duration = "myDuration"
            savingThrow = "mySavingThrow"
            spellResistance = SpellResistance.NONE
            description = "myDescription"
            materialComponent = "myMaterialComponent"
            focus = "myFocus"
        }

        val spelllistService: SpelllistService = mock()
        whenever(spelllistService.findSpellById(any(), any())).doReturn(spell)
        whenever(spelllistService.getSpellDescription(spell)).doReturn(spell)

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySpellSchool(spell)).doReturn("mySchool")
        whenever(displayService.getDisplaySpellComponents(spell)).doReturn("myComponents")
        whenever(displayService.getDisplayCastingTime(spell.castingTime)).doReturn("myCastingTime")
        whenever(displayService.getDisplayRange(spell.range)).doReturn("myRange")
        whenever(displayService.getDisplaySpellResistance(spell.spellResistance)).doReturn("mySpellResistance")


        val gameSystem: GameSystem = mock()
        whenever(gameSystem.spelllistService).doReturn(spelllistService)
        whenever(gameSystem.displayService).doReturn(displayService)
        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, SpellActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("mySpell"))))
        onView(withId(R.id.spell_school)).check(matches(withText("School: mySchool")))
        onView(withId(R.id.spell_components)).check(matches(withText("Components: myComponents")))
        onView(withId(R.id.spell_casting_time)).check(matches(withText("Casting Time: myCastingTime")))
        onView(withId(R.id.spell_range)).check(matches(withText("Range: myRange")))
        onView(withId(R.id.spell_effect)).check(matches(withText("Effect: myEffect")))
        onView(withId(R.id.spell_duration)).check(matches(withText("Duration: myDuration")))
        onView(withId(R.id.spell_saving_throw)).check(matches(withText("Saving Throw: mySavingThrow")))
        onView(withId(R.id.spell_spell_resistance)).check(matches(withText("Spell Resistance: mySpellResistance")))
        onView(withId(R.id.spell_description)).check(matches(withText("Description: myDescription")))
        onView(withId(R.id.spell_mat_component)).check(matches(withText("Material Component: myMaterialComponent")))
        onView(withId(R.id.spell_focus)).check(matches(withText("Focus: myFocus")))
    }

}