package com.android.ash.charactersheet.gui.sheet.combat

import android.view.View
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.gui.widget.numberview.SimpleNumberViewController
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView
import com.android.ash.charactersheet.gui.widget.numberview.SumNumberView
import com.d20charactersheet.framework.boc.model.Character
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CombatEditPanelKoinTest : KoinTest {

    private val characterHolder: CharacterHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun saveData_DnDv35_saveAllData() {

        // arrange
        val hitPoints: SumNumberView = mock()
        val hitPointsController: SimpleNumberViewController = mock()
        whenever(hitPointsController.number).thenReturn(10)
        whenever(hitPoints.controller).thenReturn(hitPointsController)

        val maxHitPoints: SumNumberView = mock()
        val maxHitPointsController: SimpleNumberViewController = mock()
        whenever(maxHitPointsController.number).thenReturn(20)
        whenever(maxHitPoints.controller).thenReturn(maxHitPointsController)

        val armorClass: StepNumberView = mock()
        val armorClassController: SimpleNumberViewController = mock()
        whenever(armorClassController.number).thenReturn(30)
        whenever(armorClass.controller).thenReturn(armorClassController)

        val initiative: StepNumberView = mock()
        val initiativeController: SimpleNumberViewController = mock()
        whenever(initiativeController.number).thenReturn(40)
        whenever(initiative.controller).thenReturn(initiativeController)

        val cmb: StepNumberView = mock()
        val cmbController: SimpleNumberViewController = mock()
        whenever(cmbController.number).thenReturn(50)
        whenever(cmb.controller).thenReturn(cmbController)

        val cmd: StepNumberView = mock()
        val cmdController: SimpleNumberViewController = mock()
        whenever(cmdController.number).thenReturn(60)
        whenever(cmd.controller).thenReturn(cmdController)

        val view: View = mock()
        whenever(view.findViewById<SumNumberView>(R.id.combat_hitpoints)).thenReturn(hitPoints)
        whenever(view.findViewById<SumNumberView>(R.id.combat_max_hitpoints)).thenReturn(maxHitPoints)
        whenever(view.findViewById<StepNumberView>(R.id.combat_armorclass)).thenReturn(armorClass)
        whenever(view.findViewById<StepNumberView>(R.id.combat_initiative)).thenReturn(initiative)
        whenever(view.findViewById<StepNumberView>(R.id.combat_cmb)).thenReturn(cmb)
        whenever(view.findViewById<StepNumberView>(R.id.combat_cmd)).thenReturn(cmd)

        val character = Character()
        characterHolder.character = character

        // act
        DnDv35CombatEditPanel().saveData(view)

        // assert
        assertThat(character.hitPoints).isEqualTo(10)
        assertThat(character.maxHitPoints).isEqualTo(20)
        assertThat(character.armorClass).isEqualTo(30)
        assertThat(character.initiativeModifier).isEqualTo(40)
        assertThat(character.cmbModifier).isEqualTo(50)
        assertThat(character.cmdModifier).isEqualTo(60)
    }

    @Test
    fun saveData_DnD5e_saveAllData() {

        // arrange
        val hitPoints: SumNumberView = mock()
        val hitPointsController: SimpleNumberViewController = mock()
        whenever(hitPointsController.number).thenReturn(10)
        whenever(hitPoints.controller).thenReturn(hitPointsController)

        val maxHitPoints: SumNumberView = mock()
        val maxHitPointsController: SimpleNumberViewController = mock()
        whenever(maxHitPointsController.number).thenReturn(20)
        whenever(maxHitPoints.controller).thenReturn(maxHitPointsController)

        val armorClass: StepNumberView = mock()
        val armorClassController: SimpleNumberViewController = mock()
        whenever(armorClassController.number).thenReturn(30)
        whenever(armorClass.controller).thenReturn(armorClassController)

        val initiative: StepNumberView = mock()
        val initiativeController: SimpleNumberViewController = mock()
        whenever(initiativeController.number).thenReturn(40)
        whenever(initiative.controller).thenReturn(initiativeController)

        val view: View = mock()
        whenever(view.findViewById<SumNumberView>(R.id.combat_hitpoints)).thenReturn(hitPoints)
        whenever(view.findViewById<SumNumberView>(R.id.combat_max_hitpoints)).thenReturn(maxHitPoints)
        whenever(view.findViewById<StepNumberView>(R.id.combat_armorclass)).thenReturn(armorClass)
        whenever(view.findViewById<StepNumberView>(R.id.combat_initiative)).thenReturn(initiative)
        whenever(view.findViewById<StepNumberView>(R.id.combat_cmb)).thenReturn(null)
        whenever(view.findViewById<StepNumberView>(R.id.combat_cmd)).thenReturn(null)

        val character = Character()
        characterHolder.character = character

        // act
        DnD5eCombatEditPanel().saveData(view)

        // assert
        assertThat(character.hitPoints).isEqualTo(10)
        assertThat(character.maxHitPoints).isEqualTo(20)
        assertThat(character.armorClass).isEqualTo(30)
        assertThat(character.initiativeModifier).isEqualTo(40)
        assertThat(character.cmbModifier).isEqualTo(0)
        assertThat(character.cmdModifier).isEqualTo(0)
    }

}