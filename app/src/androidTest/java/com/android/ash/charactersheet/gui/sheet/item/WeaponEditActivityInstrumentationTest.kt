package com.android.ash.charactersheet.gui.sheet.item

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.*
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.Equipment
import com.d20charactersheet.framework.boc.model.ItemGroup
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.ItemService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.Matchers
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class WeaponEditActivityInstrumentationTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()
    private val preferenceServiceHolder by inject<PreferenceServiceHolder>()

    private lateinit var scenario: ActivityScenario<WeaponEditActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayItem(any())).doReturn(("myWeapon"))

        val weapon = Weapon().apply { id = 1; name = "myWeapon" }

        val itemService: ItemService = mock()
        whenever(itemService.getEquipableWeapons(any())).doReturn(listOf(weapon))

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.itemService).doReturn(itemService)
        gameSystemHolder.gameSystem = gameSystem

        preferenceServiceHolder.preferenceService = mock()

        characterHolder.character = Character().apply {
            name = "myCharacter"
            equipment = Equipment().apply { weapons = listOf(ItemGroup().apply { item = weapon; number = 1 }) }
        }

        scenario = ActivityScenario.launch(WeaponEditActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myCharacter"))))
        onView(withId(R.id.item_name)).check(matches(withText("myWeapon")))
        onView(Matchers.allOf(withParent(withId(R.id.item_number)), withClassName(Matchers.endsWith("TextView")))).check(matches(withText("1")))
    }

}