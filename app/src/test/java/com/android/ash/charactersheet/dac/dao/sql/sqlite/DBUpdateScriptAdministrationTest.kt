package com.android.ash.charactersheet.dac.dao.sql.sqlite

import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DBUpdateScriptAdministrationTest {

    @Test
    fun getUpdateScript_noUpdateRequired_returnNoUpdateScript() {
        // arrange
        val underTest = DBUpdateScriptAdministration(GameSystemType.DNDV35.updateScripts)

        // act
        val updateScript = underTest.getUpdateScript(1)

        // assert
        assertThat(updateScript).isEqualTo(DBUpdateScriptAdministration.NO_UPDATE_SCRIPT)
    }

    @Test
    fun getUpdateScript_requestUpdateFrom59To60_returnScript59To60() {
        // arrange
        val underTest = DBUpdateScriptAdministration(GameSystemType.DNDV35.updateScripts)

        // act
        val updateScript = underTest.getUpdateScript(59)

        // assert
        assertThat(updateScript).isEqualTo(R.raw.dndv35_upgrade_59_to_60)
    }

}