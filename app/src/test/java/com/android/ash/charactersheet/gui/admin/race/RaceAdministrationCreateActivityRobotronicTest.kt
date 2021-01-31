package com.android.ash.charactersheet.gui.admin.race

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.d20charactersheet.framework.boc.model.CharacterClass.AnyCharacterClass.ANY_CHARACTER_CLASS
import com.d20charactersheet.framework.boc.model.Size
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class RaceAdministrationCreateActivityRobotronicTest {

    @Test
    fun createForm() {
        // Act
        val race = RaceAdministrationCreateActivity().createForm()

        // Assert
        assertThat(race.name).isEmpty()
        assertThat(race.size).isEqualTo(Size.MEDIUM)
        assertThat(race.baseLandSpeed).isEqualTo(30)
        assertThat(race.favoredCharacterClass).isEqualTo(ANY_CHARACTER_CLASS)
        assertThat(race.abilities).isEmpty()
    }

}