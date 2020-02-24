package com.android.ash.charactersheet.gui.admin.feat

import com.d20charactersheet.framework.boc.model.Feat
import com.d20charactersheet.framework.boc.model.FeatType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FeatAdministrationCreateActivityTest {

    @Test
    fun createForm() {
        // Act
        val feat = FeatAdministrationCreateActivity().createForm() as Feat

        // Assert
        assertThat(feat.name).isEmpty()
        assertThat(feat.featType).isEqualTo(FeatType.GENERAL)
        assertThat(feat.benefit).isEmpty()
        assertThat(feat.prerequisit).isEmpty()
        assertThat(feat.isFighterBonus).isFalse()
        assertThat(feat.isStack).isFalse()
        assertThat(feat.isMultiple).isFalse()
    }

}