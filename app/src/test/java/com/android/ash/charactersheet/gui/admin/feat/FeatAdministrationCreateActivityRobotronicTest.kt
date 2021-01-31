package com.android.ash.charactersheet.gui.admin.feat

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.d20charactersheet.framework.boc.model.Feat
import com.d20charactersheet.framework.boc.model.FeatType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class FeatAdministrationCreateActivityRobotronicTest {

    @Test
    fun createForm() {
        // Act
        val feat = FeatAdministrationCreateActivity().createForm() as Feat

        // Assert
        assertThat(feat.name).isEmpty()
        assertThat(feat.featType).isEqualTo(FeatType.GENERAL)
        assertThat(feat.benefit).isEmpty()
        assertThat(feat.prerequisit).isEmpty()
        assertThat(feat.isFighterBonus).isFalse
        assertThat(feat.isStack).isFalse
        assertThat(feat.isMultiple).isFalse
    }

}