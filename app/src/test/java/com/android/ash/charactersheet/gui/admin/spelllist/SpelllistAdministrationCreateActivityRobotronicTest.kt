package com.android.ash.charactersheet.gui.admin.spelllist

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.d20charactersheet.framework.boc.model.Spelllist
import com.d20charactersheet.framework.boc.service.GameSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class SpelllistAdministrationCreateActivityRobotronicTest {

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