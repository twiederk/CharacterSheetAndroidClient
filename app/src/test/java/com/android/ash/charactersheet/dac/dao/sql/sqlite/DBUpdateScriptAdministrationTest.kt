package com.android.ash.charactersheet.dac.dao.sql.sqlite

import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DBUpdateScriptAdministrationTest {

    @Test
    fun getUpdateScript_noUpdateRequired_returnNoUpdateScript() {
        // arrange
        val underTest = DBUpdateScriptAdministration(
            GameSystemType.DNDV35.updateScriptResources,
            GameSystemType.DNDV35.updateImageResources
        )
        // act
        val scriptResource = underTest.getUpdateScript(1)

        // assert
        assertThat(scriptResource).isNull()
    }

    @Test
    fun getUpdateScript_requestUpdateFrom59To60_returnScript59To60() {
        // arrange
        val underTest = DBUpdateScriptAdministration(
            GameSystemType.DNDV35.updateScriptResources,
            GameSystemType.DNDV35.updateImageResources
        )
        // act
        val scriptResource = underTest.getUpdateScript(59)

        // assert
        assertThat(scriptResource).isInstanceOf(RawScriptResource::class.java)
        val rawScriptResource = scriptResource as RawScriptResource
        assertThat(rawScriptResource.resourceId).isEqualTo(R.raw.dndv35_upgrade_59_to_60)
    }

    @Test
    fun getImageResources_requestUpdateFrom72To72_returnImageResources() {
        // arrange
        val underTest = DBUpdateScriptAdministration(
            GameSystemType.DNDV35.updateScriptResources,
            GameSystemType.DNDV35.updateImageResources
        )

        // act
        val imageResources = underTest.getUpdateImage(72)

        // assert
        assertThat(imageResources?.imageResources).hasSize(36)

    }
}