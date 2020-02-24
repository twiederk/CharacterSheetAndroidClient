package com.android.ash.charactersheet.gui.admin.spelllist

import com.d20charactersheet.framework.boc.model.Spelllist
import com.d20charactersheet.framework.boc.service.GameSystem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SpelllistAdministrationCreateActivityTest {

    @Test
    fun createForm() {
        // Arrange
        val underTest = SpelllistAdministrationCreateActivity()
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.spelllistService).doReturn(mock())
        underTest.gameSystem = gameSystem

        // Act
        val spelllist = underTest.createForm() as Spelllist

        // Assert
        assertThat(spelllist.name).isEmpty()
        assertThat(spelllist.shortname).isEmpty()
        assertThat(spelllist.spellsByLevel).isEmpty()
    }
}