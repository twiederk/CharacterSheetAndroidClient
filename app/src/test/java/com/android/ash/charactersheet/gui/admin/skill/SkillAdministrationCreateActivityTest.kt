package com.android.ash.charactersheet.gui.admin.skill

import com.d20charactersheet.framework.boc.model.Attribute
import com.d20charactersheet.framework.boc.model.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SkillAdministrationCreateActivityTest {

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