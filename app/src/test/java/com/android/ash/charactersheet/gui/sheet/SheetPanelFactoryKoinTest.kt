package com.android.ash.charactersheet.gui.sheet

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.gui.sheet.combat.DnD5eCombatEditPanel
import com.android.ash.charactersheet.gui.sheet.combat.DnD5eCombatPanel
import com.android.ash.charactersheet.gui.sheet.combat.DnDv35CombatEditPanel
import com.android.ash.charactersheet.gui.sheet.combat.DnDv35CombatPanel
import com.android.ash.charactersheet.gui.sheet.save.DnD5eSavePanel
import com.android.ash.charactersheet.gui.sheet.save.DnDv35SavePanel
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class SheetPanelFactoryKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()

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
    fun createCombatPanel_DnD5e_createDnD5eCombatPanel() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DND5E

        // act
        val combatPanel = SheetPanelFactory().createCombatPanel()

        // assert
        assertThat(combatPanel).isInstanceOf(DnD5eCombatPanel::class.java)
    }

    @Test
    fun createCombatPanel_DnDv35_createDnDv35CombatPanel() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        // act
        val combatPanel = SheetPanelFactory().createCombatPanel()

        // assert
        assertThat(combatPanel).isInstanceOf(DnDv35CombatPanel::class.java)
    }

    @Test
    fun createSavePanel_DnD5e_createDnD5eSavePanel() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DND5E

        // act
        val combatPanel = SheetPanelFactory().createSavePanel()

        // assert
        assertThat(combatPanel).isInstanceOf(DnD5eSavePanel::class.java)
    }

    @Test
    fun createSavePanel_DnDv35_createDnDv35SavePanel() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        // act
        val combatPanel = SheetPanelFactory().createSavePanel()

        // assert
        assertThat(combatPanel).isInstanceOf(DnDv35SavePanel::class.java)
    }

    @Test
    fun createCombatEditPanel_DnD5e_createDnD5eSavePanel() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DND5E

        // act
        val combatPanel = SheetPanelFactory().createCombatEditPanel()

        // assert
        assertThat(combatPanel).isInstanceOf(DnD5eCombatEditPanel::class.java)
    }

    @Test
    fun createCombatEditPanel_DnDv35_createDnDv35SavePanel() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        // act
        val combatPanel = SheetPanelFactory().createCombatEditPanel()

        // assert
        assertThat(combatPanel).isInstanceOf(DnDv35CombatEditPanel::class.java)
    }

}