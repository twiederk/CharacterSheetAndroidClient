package com.android.ash.charactersheet.gui.admin.ability

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
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.AbilityService
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

class AbilityAdministrationEditSpelllistActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<AbilityAdministrationEditSpelllistActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val spelllist = Spelllist().apply {
            id = 1
            name = "mySpelllist"
        }

        val knownSpellsTable = KnownSpellsTable().apply {
            id = 1
            name = "myKnownSpellsTable"
        }

        val spellsPerDayTable = SpellsPerDayTable().apply {
            id = 1
            name = "mySpellsPerDayTable"
        }

        val ability = SpelllistAbility()
        ability.name = "myAbility"
        ability.abilityType = AbilityType.EXTRAORDINARY
        ability.spelllist = spelllist
        ability.spellAttribute = Attribute.INTELLIGENCE
        ability.castingType = CastingType.PREPARED
        ability.spellSource = SpellSource.ARCANE
        ability.schools = EnumSet.allOf(School::class.java)
        ability.knownSpellsTable = knownSpellsTable
        ability.spellsPerDayTable = spellsPerDayTable
        ability.description = "myDescription"

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySchool(any())).doReturn("mySchool")

        val abilityService: AbilityService = mock()
        whenever(abilityService.getAbilityById(any(), any())).doReturn(ability)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.abilityService).doReturn(abilityService)
        whenever(gameSystem.allSpelllists).doReturn(listOf(spelllist))
        whenever(gameSystem.allKnownSpellsTables).doReturn(listOf(knownSpellsTable))
        whenever(gameSystem.allSpellsPerDayTables).doReturn(listOf(spellsPerDayTable))

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, AbilityAdministrationEditSpelllistActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Spell List Ability"))))
        onView(withId(R.id.ability_administration_name)).check(matches(withText("myAbility")))
        onView(withId(R.id.ability_administration_type)).check(matches(withSpinnerText("EXTRAORDINARY")))
        onView(withId(R.id.ability_administration_spelllist)).check(matches(withSpinnerText("mySpelllist")))
        onView(withId(R.id.ability_administration_attribute)).check(matches(withSpinnerText("INTELLIGENCE")))
        onView(withId(R.id.ability_administration_castingtype)).check(matches(withSpinnerText("PREPARED")))
        onView(withId(R.id.ability_administration_spellsource)).check(matches(withSpinnerText("ARCANE")))
        onView(withId(R.id.ability_administration_school)).check(matches(withSpinnerText("Any school")))
        onView(withId(R.id.ability_administration_known_spells_table)).check(matches(withSpinnerText("myKnownSpellsTable")))
        onView(withId(R.id.ability_administration_spells_per_day_table)).check(matches(withSpinnerText("mySpellsPerDayTable")))
        onView(withId(R.id.ability_administration_desc)).check(matches(withText("myDescription")))
    }

}