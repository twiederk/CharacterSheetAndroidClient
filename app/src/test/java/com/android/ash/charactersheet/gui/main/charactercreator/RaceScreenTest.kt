package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.Ability
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RaceScreenTest {

    @Test
    fun getAbilityScoreIncrease_noAbilities_returnEmptyString() {
        // act
        val result: String = getAbilityScoreIncrease(listOf())

        // assert
        assertThat(result).isBlank
    }

    @Test
    fun getAbilityScoreIncrease_oneAbilityScoreIncrease_returnEmptyString() {
        // arrange
        val abilities = listOf(
            Ability().apply {
                name = "Ability Score Increase (Con +2)"
                description = "Constitution +2."
            }
        )

        // act
        val result: String = getAbilityScoreIncrease(abilities)

        // assert
        assertThat(result).isEqualTo("Constitution +2.")
    }

}