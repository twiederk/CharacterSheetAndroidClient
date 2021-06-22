package com.android.ash.charactersheet.gui.main.exportimport

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.service.GameSystem
import org.hamcrest.Matchers.startsWith
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ExportEquipmentActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<ExportEquipmentActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val weapon = Weapon()
        weapon.id = 1
        weapon.name = "myWeapon"
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allWeapons).doReturn(listOf(weapon))
        gameSystemHolder.gameSystem = gameSystem

        scenario = ActivityScenario.launch(ExportEquipmentActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Export Equipment"))))
        onView(withId(R.id.export_export_directory)).check(matches(withText(startsWith("Export Directory:"))))
        onView(withId(R.id.listitem_export_checkbox))
                .check(matches(withText("myWeapon")))
                .check(matches(isNotChecked()))
    }

}