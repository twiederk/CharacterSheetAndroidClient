package com.android.ash.charactersheet.gui.admin.spell

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.d20charactersheet.framework.boc.model.CastingTime
import com.d20charactersheet.framework.boc.model.Descriptor
import com.d20charactersheet.framework.boc.model.School
import com.d20charactersheet.framework.boc.model.Spell
import com.d20charactersheet.framework.boc.model.SpellResistance
import com.d20charactersheet.framework.boc.model.SubSchool
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class SpellAdministrationCreateActivityRobotronicTest {

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
        assertThat(spell.range).isEqualTo("Close (25 ft. + 5 ft./2 levels)")
        assertThat(spell.effect).isEmpty()
        assertThat(spell.duration).isEmpty()
        assertThat(spell.savingThrow).isEmpty()
        assertThat(spell.spellResistance).isEqualTo(SpellResistance.NONE)
        assertThat(spell.description).isEmpty()
        assertThat(spell.materialComponent).isEmpty()
        assertThat(spell.shortDescription).isEmpty()
    }

}