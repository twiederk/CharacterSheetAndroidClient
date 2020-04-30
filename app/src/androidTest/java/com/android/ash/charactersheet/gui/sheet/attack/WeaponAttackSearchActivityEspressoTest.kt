package com.android.ash.charactersheet.gui.sheet.attack

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.*
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.model.WeaponCategory
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class WeaponAttackSearchActivityEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()
    private val characterHolder by inject<CharacterHolder>()
    private val preferenceServiceHolder by inject<PreferenceServiceHolder>()

    private lateinit var scenario: ActivityScenario<WeaponAttackSearchActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplayItem(any())).doReturn("myWeapon")

        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem
        whenever(gameSystem.displayService).doReturn(displayService)
        whenever(gameSystem.allWeapons).doReturn(listOf(Weapon().apply { weaponCategory = WeaponCategory.NORMAL_WEAPON }))

        preferenceServiceHolder.preferenceService = mock()

        characterHolder.character = Character().apply {
            name = "myCharacter"
        }

        scenario = ActivityScenario.launch(WeaponAttackSearchActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("myCharacter"))))
        onView(withId(R.id.name)).check(matches(withText("myWeapon")))
    }

}