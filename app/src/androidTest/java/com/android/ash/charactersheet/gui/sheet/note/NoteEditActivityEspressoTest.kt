package com.android.ash.charactersheet.gui.sheet.note

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
import com.d20charactersheet.framework.boc.model.Note
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import java.text.SimpleDateFormat

class NoteEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()
    private val preferenceServiceHolder by inject<PreferenceServiceHolder>()

    private lateinit var scenario: ActivityScenario<NoteEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem

        preferenceServiceHolder.preferenceService = mock()

        characterHolder.character = Character().apply {
            name = "myCharacter"
        }

        val note = Note().apply {
            date = SimpleDateFormat("yyyy-MM-dd").parse("2020-05-31")
            text = "myNote"
        }

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, NoteEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, note)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myCharacter"))))
        onView(withId(R.id.note_date)).check(matches(withText("05/31/2020 00:00")))
        onView(withId(R.id.note_text)).check(matches(withText("myNote")))
    }

}