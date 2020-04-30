package com.android.ash.charactersheet.gui.sheet.clazz

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.ClassLevel
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ClassLevelCreateActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<ClassLevelCreateActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val characterClass = CharacterClass().apply {
            id = 1
            name = "myCharacterClass"
            classAbilities = listOf()
        }

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allCharacterClasses).doReturn(listOf(characterClass))
        gameSystemHolder.gameSystem = gameSystem

        characterHolder.character = Character().apply {
            classLevels = listOf(ClassLevel(CharacterClass().apply { id = 0; classAbilities = listOf() }, 1))
        }

        scenario = ActivityScenario.launch(ClassLevelCreateActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Select Class"))))
        onView(withId(R.id.name)).check(matches(withText("myCharacterClass")))
    }

}