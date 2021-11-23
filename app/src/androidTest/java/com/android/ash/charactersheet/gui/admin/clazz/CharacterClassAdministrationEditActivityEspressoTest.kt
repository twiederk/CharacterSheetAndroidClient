package com.android.ash.charactersheet.gui.admin.clazz

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.Constants
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.BaseAttackBonus
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Die
import com.d20charactersheet.framework.boc.model.Save
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.endsWith
import org.hamcrest.Matchers.isEmptyString
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

class CharacterClassAdministrationEditActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<CharacterClassAdministrationEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val characterClass = CharacterClass()
        characterClass.name = ""
        characterClass.id = 1
        characterClass.alignments = EnumSet.allOf(Alignment::class.java)
        characterClass.hitDie = Die.D8
        characterClass.baseAttackBonus = BaseAttackBonus.AVERAGE
        characterClass.saves = EnumSet.noneOf(Save::class.java)
        characterClass.classAbilities = ArrayList()
        characterClass.skillPointsPerLevel = 4
        characterClass.skills = ArrayList()

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayAlignment(any())).doReturn("MyAlignment")
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.allCharacterClasses).doReturn(listOf(characterClass))

        gameSystemHolder.gameSystem = gameSystem

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, CharacterClassAdministrationEditActivity::class.java).putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, 1)
        scenario = ActivityScenario.launch(intent)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Edit Character Class"))))
        onView(withId(R.id.character_class_administration_name)).check(
            matches(
                withText(
                    isEmptyString()
                )
            )
        )
        onView(withId(R.id.character_class_administration_alignments)).check(matches(withText("MyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment\nMyAlignment")))
        onView(withId(R.id.character_class_administration_hitdie)).check(matches(withSpinnerText("D8")))
        onView(withId(R.id.character_class_administration_baseattackbonus)).check(
            matches(
                withSpinnerText("AVERAGE")
            )
        )
        onView(withId(R.id.character_class_administration_save_strength)).check(matches(isNotChecked()))
        onView(withId(R.id.character_class_administration_save_dexterity)).check(
            matches(
                isNotChecked()
            )
        )
        onView(withId(R.id.character_class_administration_save_constitution)).check(
            matches(
                isNotChecked()
            )
        )
        onView(withId(R.id.character_class_administration_save_intelligence)).check(
            matches(
                isNotChecked()
            )
        )
        onView(withId(R.id.character_class_administration_save_wisdom)).check(
            matches(
                isNotChecked()
            )
        )
        onView(withId(R.id.character_class_administration_save_charisma)).check(
            matches(
                isNotChecked()
            )
        )
        onView(withId(R.id.character_class_administration_abilities)).check(matches(withText("Abilities:\n")))
        onView(
            allOf(
                withParent(withId(R.id.character_class_administration_skillpoints)),
                withClassName(endsWith("TextView"))
            )
        ).check(matches(withText("4")))
        onView(withId(R.id.character_class_administration_skills)).check(matches(withText("Class Skills:\n")))
    }

}