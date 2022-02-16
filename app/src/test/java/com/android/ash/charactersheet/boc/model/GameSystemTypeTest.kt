package com.android.ash.charactersheet.boc.model

import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.dac.dao.sql.sqlite.RawScriptResource
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameSystemTypeTest {

    @Test
    fun values_checkNumbersOfGameSystems_returnsAllSupportedGameSystems() {

        // act
        val gameSystemTypes = GameSystemType.values()

        // assert
        assertThat(gameSystemTypes).hasSize(3)
        assertThat(gameSystemTypes[0].getName()).isEqualTo("DnD v.3.5")
        assertThat(gameSystemTypes[1].getName()).isEqualTo("Pathfinder")
        assertThat(gameSystemTypes[2].getName()).isEqualTo("DnD 5e")
    }

    @Test
    fun dnd5e_checkConfiguration_properConfigurationODnD5e() {

        // act
        val dnd5e = GameSystemType.DND5E

        // assert
        assertThat(dnd5e.id).isEqualTo(3)
        assertThat(dnd5e.getName()).isEqualTo("DnD 5e")
        assertThat(dnd5e.databaseName).isEqualTo("dnd5e_db")

        assertThat((dnd5e.createScriptResources[0] as RawScriptResource).resourceId).isEqualTo(R.raw.create_database)
        assertThat((dnd5e.createScriptResources[1] as RawScriptResource).resourceId).isEqualTo(R.raw.dnd5e_phb_data)
        assertThat((dnd5e.createScriptResources[2] as RawScriptResource).resourceId).isEqualTo(R.raw.dnd5e_phb_spell)
        assertThat((dnd5e.createScriptResources[3] as RawScriptResource).resourceId).isEqualTo(R.raw.dnd5e_phb_character)
    }

}