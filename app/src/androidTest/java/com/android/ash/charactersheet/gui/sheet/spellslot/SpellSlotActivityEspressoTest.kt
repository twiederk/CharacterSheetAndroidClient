package com.android.ash.charactersheet.gui.sheet.spellslot

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.*
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.SpellSlot
import com.d20charactersheet.framework.boc.model.SpelllistAbility
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class SpellSlotActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<SpellSlotActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.ruleService).doReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        characterHolder.character = Character().apply {
            spellSlots = listOf(SpellSlot().apply {
                id = 1
                spelllistAbilities = listOf(SpelllistAbility().apply { name = "mySpelllistAbility" })
            })
        }

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, SpellSlotActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Spell Slot"))))
        onView(withId(R.id.spellslot_abilities)).check(matches(withText("mySpelllistAbility")))
        onView(withId(R.id.spellslot_spell)).check(matches(withSpinnerText("EMPTY SPELL")))
    }

}