package com.android.ash.charactersheet.gui.admin.item.armor

import com.d20charactersheet.framework.boc.model.Armor
import com.d20charactersheet.framework.boc.model.ArmorType
import com.d20charactersheet.framework.boc.model.QualityType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ArmorAdministrationCreateActivityTest {

    @Test
    fun createForm() {
        // Act
        val armor = ArmorAdministrationCreateActivity().createForm() as Armor

        // Assert
        assertThat(armor.name).isEmpty()
        assertThat(armor.armorType).isEqualTo(ArmorType.LIGHT)
        assertThat(armor.cost).isEqualTo(0F)
        assertThat(armor.weight).isEqualTo(1F)
        assertThat(armor.qualityType).isEqualTo(QualityType.NORMAL)
        assertThat(armor.armorBonus).isEqualTo(1)
        assertThat(armor.armorCheckPenalty).isEqualTo(0)
        assertThat(armor.description).isEmpty()
    }

}