package com.android.ash.charactersheet.gui.admin.race

import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Size
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class RaceAdministrationCreateActivityTest {

    @Test
    fun createForm() {
        // Act
        val race = RaceAdministrationCreateActivity().createForm()

        // Assert
        assertThat(race.name).isEmpty()
        assertThat(race.size).isEqualTo(Size.MEDIUM)
        assertThat(race.baseLandSpeed).isEqualTo(30)
        assertThat(race.favoredCharacterClass).isEqualTo(CharacterClass.ANY_CHARACTER_CLASS)
        assertThat(race.abilities).isEmpty()
    }

}