package com.android.ash.charactersheet.gui.admin.clazz

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.d20charactersheet.framework.boc.model.*
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.*

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class CharacterClassAdministrationCreateActivityRobotronicTest {

    @Test
    fun createForm() {
        // Act
        val characterClass = CharacterClassAdministrationCreateActivity().createForm() as CharacterClass

        // Assert
        Assertions.assertThat(characterClass.name).isEmpty()
        Assertions.assertThat(characterClass.alignments).isEqualTo(EnumSet.allOf(Alignment::class.java))
        Assertions.assertThat(characterClass.hitDie).isEqualTo(Die.D8)
        Assertions.assertThat(characterClass.baseAttackBonus).isEqualTo(BaseAttackBonus.AVERAGE)
        Assertions.assertThat(characterClass.saves).isEqualTo(EnumSet.noneOf(Save::class.java))
        Assertions.assertThat(characterClass.classAbilities).isEmpty()
        Assertions.assertThat(characterClass.skillPointsPerLevel).isEqualTo(4)
        Assertions.assertThat(characterClass.skills).isEmpty()
    }

}