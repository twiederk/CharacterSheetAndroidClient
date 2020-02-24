package com.android.ash.charactersheet.gui.admin.spell

import com.d20charactersheet.framework.boc.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SpellAdministrationCreateActivityTest {

    @Test
    fun createForm() {
        // Act
        val spell = SpellAdministrationCreateActivity().createForm() as Spell

        // Assert
        assertThat(spell.name).isEmpty()
        assertThat(spell.school).isEqualTo(School.ABJURATION)
        assertThat(spell.subSchool).isEqualTo(SubSchool.NONE)
        assertThat(spell.descriptors).containsExactly(Descriptor.NONE)
        assertThat(spell.castingTime).isEqualTo(CastingTime.ONE_STANDARD_ACTION)
        assertThat(spell.range).isEqualTo(Range.CLOSE)
        assertThat(spell.effect).isEmpty()
        assertThat(spell.duration).isEmpty()
        assertThat(spell.savingThrow).isEmpty()
        assertThat(spell.spellResistance).isEqualTo(SpellResistance.NONE)
        assertThat(spell.description).isEmpty()
        assertThat(spell.materialComponent).isEmpty()
        assertThat(spell.shortDescription).isEmpty()
    }

}