package com.android.ash.charactersheet.gui.admin.skill

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.d20charactersheet.framework.boc.model.Attribute
import com.d20charactersheet.framework.boc.model.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class SkillAdministrationCreateActivityRobotronicTest {

    @Test
    fun createForm() {
        // Act
        val skill = SkillAdministrationCreateActivity().createForm() as Skill

        // Assert
        assertThat(skill.name).isEmpty()
        assertThat(skill.attribute).isEqualTo(Attribute.STRENGTH)
        assertThat(skill.description).isEmpty()
    }

}