package com.android.ash.charactersheet.gui.widget.numberview

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ZeroAndNegativeNumberViewControllerTest {

    @Test
    fun increase_increaseMinus5_resultIsMinus4() {
        // Arrange
        val underTest = ZeroAndNegativeNumberViewController(-5)

        // Act
        underTest.increase()

        // Assert
        assertThat(underTest.number).isEqualTo(-4)
    }

    @Test
    fun increase_increaseZero_resultIsZero() {
        // Arrange
        val underTest = ZeroAndNegativeNumberViewController(0)

        // Act
        underTest.increase()

        // Assert
        assertThat(underTest.number).isEqualTo(0)
    }

    @Test
    fun increase_increaseMinus5By2_resultIsMinus3() {
        // Arrange
        val underTest = ZeroAndNegativeNumberViewController(-5)

        // Act
        underTest.increase(2)

        // Assert
        assertThat(underTest.number).isEqualTo(-3)
    }

    @Test
    fun increase_increaseZeroBy2_resultIsZero() {
        // Arrange
        val underTest = ZeroAndNegativeNumberViewController(0)

        // Act
        underTest.increase(2)

        // Assert
        assertThat(underTest.number).isEqualTo(0)
    }
}