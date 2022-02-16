package com.android.ash.charactersheet.gui.admin.spell

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.Matchers.isEmptyString
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SpellAdministrationCreateActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SpellAdministrationCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(SpellAdministrationCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Spell"))))
        onView(withId(R.id.spell_administration_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spell_administration_school)).check(matches(withSpinnerText("ABJURATION")))
        onView(withId(R.id.spell_administration_subschool)).check(matches(withSpinnerText("NONE")))
        onView(withId(R.id.spell_administration_descriptor_acid)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_air)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_chaotic)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_cold)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_darkness)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_death)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_earth)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_electricity)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_evil)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_fear)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_fire)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_force)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_good)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_languagedependent)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_lawful)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_light)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_mindaffecting)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_sonic)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_descriptor_water)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_component_verbal)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_component_somatic)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_component_material)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_component_focus)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_component_divinefocus)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_component_xpcost)).check(matches(isNotChecked()))
        onView(withId(R.id.spell_administration_castingtime)).check(matches(withSpinnerText("ONE_STANDARD_ACTION")))
        onView(withId(R.id.spell_administration_range)).check(matches(withText("Close (25 ft. + 5 ft./2 levels)")))
        onView(withId(R.id.spell_administration_spellresistance)).check(matches(withSpinnerText("NONE")))
        onView(withId(R.id.spell_administration_effect)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spell_administration_duration)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spell_administration_savingthrow)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spell_administration_materialcomponent)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spell_administration_focus)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spell_administration_shortdescription)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spell_administration_description)).check(matches(withText(isEmptyString())))
    }

}