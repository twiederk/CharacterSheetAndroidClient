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
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class CharacterClassAdministrationListActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<CharacterClassAdministrationListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val clazz = CharacterClass()
        clazz.id = 1
        clazz.name = "MyClass"
        val allClasses = listOf(clazz)
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allCharacterClasses).doReturn(allClasses)
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(CharacterClassAdministrationListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(Is.`is`("Character Class Administration"))))

        onView(withId(R.id.name))
                .check(matches(withText("MyClass")))
                .check(matches(isDisplayed()))
    }

}