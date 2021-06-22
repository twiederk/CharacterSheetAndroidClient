package com.android.ash.charactersheet.gui.sheet.appearance

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
import com.d20charactersheet.framework.boc.model.*
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

class AppearanceEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()

    private lateinit var scenario: ActivityScenario<AppearanceEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(any())).doReturn("mySex")

        val race = Race().apply { id = 1; name = "myRace" }
        val xpTable = XpTable().apply { id = 1; name = "myXpTable" }

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.allRaces).doReturn(listOf(race))
        whenever(gameSystem.allXpTables).doReturn(listOf(xpTable))
        gameSystemHolder.gameSystem = gameSystem

        characterHolder.character = Character().apply {
            name = "myCharacter"
            player = "myPlayer"
            this.race = race
            sex = Sex.MALE
            alignment = Alignment.LAWFUL_GOOD
            this.xpTable = xpTable
            experiencePoints = 100
        }

        scenario = ActivityScenario.launch(AppearanceEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Appearance"))))
        onView(withId(R.id.appearance_name)).check(matches(withText("myCharacter")))
        onView(withId(R.id.appearance_player)).check(matches(withText("myPlayer")))
        onView(withId(R.id.appearance_race)).check(matches(withSpinnerText("myRace")))
        onView(withId(R.id.appearance_sex)).check(matches(withSpinnerText("MALE")))
        onView(withId(R.id.appearance_class_level)).check(matches(withText("Edit Class")))
        onView(withId(R.id.appearance_alignment)).check(matches(withSpinnerText("LAWFUL_GOOD")))
        onView(withId(R.id.appearance_xptable)).check(matches(withSpinnerText("myXpTable")))
        onView(withId(R.id.appearance_experience)).check(matches(withText("100")))
    }

}