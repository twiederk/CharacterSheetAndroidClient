package com.android.ash.charactersheet.gui.main.characterlist

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.AndroidImageServiceImpl
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers.not
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class CharacterListActivityEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()

    private lateinit var scenario: ActivityScenario<CharacterListActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate_normalStart_showCharacterList() {

        // Arrange
        gameSystemHolder.gameSystem = null
        scenario = ActivityScenario.launch(CharacterListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Character List - DnD v.3.5"))))
        onView(withId(R.id.favorite_action_button)).check(matches(isDisplayed()))
        onView(withId(R.id.character_name)).check(matches(withText("Belvador the Summoner")))
        onView(withId(R.id.character_stats)).check(matches(withText("High Elf, Wizard (5)")))
        onView(withId(R.id.character_list_release_notes)).check(matches(not(isDisplayed())))
        onView(withId(R.id.character_list_game_system_selector)).check(matches(not(isDisplayed())))

    }

    @Test
    fun onCreate_upgrade_showReleaseNotes() {
        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allCharacters).thenReturn(mutableListOf())
        whenever(gameSystem.imageService).thenReturn(AndroidImageServiceImpl(mock()))
        gameSystemHolder.gameSystem = gameSystem


        val preferenceService: PreferenceService = mock()
        whenever(preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE)).thenReturn(GameSystemType.DNDV35.ordinal)
        preferenceServiceHolder.preferenceService = preferenceService

        val dndDbHolder: DBHelper = mock()
        whenever(dndDbHolder.isUpgrade).thenReturn(true)
        whenever(dndDbHolder.isCreate).thenReturn(false)
        gameSystemHolder.dndDbHelper = dndDbHolder
        scenario = ActivityScenario.launch(CharacterListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(withId(R.id.character_list_release_notes)).check(matches(isDisplayed()))
        onView(withId(R.id.character_list_game_system_selector)).check(matches(not(isDisplayed())))
    }

    @Test
    fun onCreate_newInstallation_showGameSystemSelection() {
        // Arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allCharacters).thenReturn(mutableListOf())
        whenever(gameSystem.imageService).thenReturn(AndroidImageServiceImpl(mock()))
        gameSystemHolder.gameSystem = gameSystem


        val preferenceService: PreferenceService = mock()
        whenever(preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE)).thenReturn(GameSystemType.DNDV35.ordinal)
        preferenceServiceHolder.preferenceService = preferenceService

        val dndDbHolder: DBHelper = mock()
        whenever(dndDbHolder.isUpgrade).thenReturn(false)
        whenever(dndDbHolder.isCreate).thenReturn(true)
        gameSystemHolder.dndDbHelper = dndDbHolder
        scenario = ActivityScenario.launch(CharacterListActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(withId(R.id.character_list_release_notes)).check(matches(not(isDisplayed())))
        onView(withId(R.id.character_list_game_system_selector)).check(matches(isDisplayed()))
        onView(withId(R.id.game_system_selector_dndv35_button)).check(matches(withText("DnD v.3.5")))
        onView(withId(R.id.game_system_selector_dnd5e_button)).check(matches(withText("DnD 5e")))
        onView(withId(R.id.game_system_selector_pathfinder_button)).check(matches(withText("Pathfinder")))
    }

    @Test
    fun fab_onClick_callCharacterCreateActivity() {

        // Arrange
        gameSystemHolder.gameSystem = null
        scenario = ActivityScenario.launch(CharacterListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        onView(withId(R.id.favorite_action_button)).perform(click())

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Create Character"))))
    }

    @Test
    fun actionBarOverflowMenu_onClickPurchasePremiumVersion_displayPurchaseDialog() {

        // Arrange
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        gameSystemHolder.gameSystem = null
        scenario = ActivityScenario.launch(CharacterListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
        openActionBarOverflowOrOptionsMenu(context)

        // Act
        onView(withText(R.string.character_list_menu_purchase)).perform(click())

        // Assert
        onView(withText(R.string.purchase_dialog_premium_version)).check(matches(isDisplayed()))
        onView(withText(R.string.purchase_dialog_purchase_button)).check(matches(isDisplayed()))
        onView(withText(R.string.purchase_dialog_restore_purchase_button)).check(matches(isDisplayed()))
        onView(withText(R.string.purchase_dialog_cancel_button)).check(matches(isDisplayed()))
    }

}