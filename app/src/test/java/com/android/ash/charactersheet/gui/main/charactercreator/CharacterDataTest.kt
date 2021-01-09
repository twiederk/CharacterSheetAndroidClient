package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.Sex
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CharacterDataTest {

    @Test
    fun constructor_initCharacter_characterWithDefaultValue() {

        // act
        val characterData = CharacterData()

        // assert
        assertThat(characterData.name).isEqualTo("")
        assertThat(characterData.player).isEqualTo("")
        assertThat(characterData.sex).isEqualTo(Sex.MALE)
        assertThat(characterData.alignment).isEqualTo(Alignment.LAWFUL_GOOD)
        assertThat(characterData.strength).isEqualTo(10)
        assertThat(characterData.dexterity).isEqualTo(10)
        assertThat(characterData.constitution).isEqualTo(10)
        assertThat(characterData.intelligence).isEqualTo(10)
        assertThat(characterData.wisdom).isEqualTo(10)
        assertThat(characterData.charisma).isEqualTo(10)
    }

}