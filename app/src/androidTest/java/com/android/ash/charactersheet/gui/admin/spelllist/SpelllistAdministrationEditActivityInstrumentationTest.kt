package com.android.ash.charactersheet.gui.admin.spelllist

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
import com.d20charactersheet.framework.boc.model.Spelllist
import com.d20charactersheet.framework.boc.service.GameSystem

import com.d20charactersheet.framework.boc.service.SpelllistService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.*
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.*

class SpelllistAdministrationEditActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<SpelllistAdministrationEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val spelllist = Spelllist()
        spelllist.name = "mySpelllist"
        spelllist.shortname = ""
        spelllist.spellsByLevel = HashMap()

        val spelllistService: SpelllistService = mock()
        whenever(spelllistService.findSpelllistById(any(), any())).doReturn(spelllist)

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(mock())
        whenever(gameSystem.spelllistService).doReturn(spelllistService)

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, SpelllistAdministrationEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Spell List"))))
        onView(withId(R.id.spelllist_administration_name)).check(matches(withText("mySpelllist")))
        onView(withId(R.id.spelllist_administration_short_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.spelllist_administration_domain)).check(matches(isNotChecked()))
        onView(allOf(withParent(withId(R.id.spelllist_administration_min_level)), withClassName(endsWith("TextView")))).check(matches(withText("0")))
        onView(allOf(withParent(withId(R.id.spelllist_administration_max_level)), withClassName(endsWith("TextView")))).check(matches(withText("0")))
    }

}