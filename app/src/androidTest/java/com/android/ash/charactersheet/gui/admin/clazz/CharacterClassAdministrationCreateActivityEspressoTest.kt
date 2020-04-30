package com.android.ash.charactersheet.gui.admin.clazz

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
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

class CharacterClassAdministrationCreateActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<CharacterClassAdministrationCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayAlignment(any())).doReturn("MyAlignment")
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(CharacterClassAdministrationCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Character Class"))))
        onView(withId(R.id.character_class_administration_name)).check(matches(withText(isEmptyString())))
        onView(withId(R.id.character_class_administration_alignments)).check(matches(withText("MyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment")))
        onView(withId(R.id.character_class_administration_hitdie)).check(matches(withSpinnerText("D8")))
        onView(withId(R.id.character_class_administration_baseattackbonus)).check(matches(withSpinnerText("AVERAGE")))
        onView(withId(R.id.character_class_administration_save_fortitide)).check(matches(isNotChecked()))
        onView(withId(R.id.character_class_administration_save_reflex)).check(matches(isNotChecked()))
        onView(withId(R.id.character_class_administration_save_will)).check(matches(isNotChecked()))
        onView(withId(R.id.character_class_administration_abilities)).check(matches(withText("Abilities:\n")))
        onView(allOf(withParent(withId(R.id.character_class_administration_skillpoints)), withClassName(endsWith("TextView")))).check(matches(withText("4")))
        onView(withId(R.id.character_class_administration_skills)).check(matches(withText("Class Skills:\n")))
    }

}