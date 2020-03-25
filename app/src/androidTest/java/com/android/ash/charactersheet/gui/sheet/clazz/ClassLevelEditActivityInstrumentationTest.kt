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
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ClassLevelEditActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<ClassLevelEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        gameSystemHolder.gameSystem = mock()

        characterHolder.character = Character().apply {
            classLevels = listOf(ClassLevel(CharacterClass().apply {
                id = 0
                name = "myCharacterClass"
                classAbilities = listOf()
            }, 1))
        }

        scenario = ActivityScenario.launch(ClassLevelEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Class Level"))))
        onView(withId(R.id.class_name)).check(matches(withText("myCharacterClass")))
        onView(allOf(withParent(withId(R.id.class_level)), withClassName(endsWith("TextView")))).check(matches(withText("1")))

    }

}