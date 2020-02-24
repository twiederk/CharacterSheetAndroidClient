package com.android.ash.charactersheet.gui.admin.item.good

import com.d20charactersheet.framework.boc.model.Good
import com.d20charactersheet.framework.boc.model.GoodType
import com.d20charactersheet.framework.boc.model.QualityType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GoodAdministrationCreateActivityTest {

    @Test
    fun createForm() {

        // Act
        val good = GoodAdministrationCreateActivity().createForm() as Good

        // Assert
        assertThat(good.name).isEmpty()
        assertThat(good.goodType).isEqualTo(GoodType.ADVENTURING_GEAR)
        assertThat(good.weight).isEqualTo(1F)
        assertThat(good.cost).isEqualTo(0F)
        assertThat(good.qualityType).isEqualTo(QualityType.NORMAL)
        assertThat(good.description).isEmpty()
    }

}
