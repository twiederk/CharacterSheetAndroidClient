package com.android.ash.charactersheet.gui.widget.numberview

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ZeroAndPositiveNumberViewControllerTest {

    @Test
    fun decrease_decrease5_resultIs4() {
        // Arrange
        val underTest = ZeroAndPositiveNumberViewController(5)

        // Act
        underTest.decrease()

        // Assert
        assertThat(underTest.number).isEqualTo(4)
    }

    @Test
    fun decrease_decrease0_resultIs() {
        // Arrange
        val underTest = ZeroAndPositiveNumberViewController(0)

        // Act
        underTest.decrease()

        // Assert
        assertThat(underTest.number).isEqualTo(0)
    }

    @Test
    fun decrease_decrease5By2_resultIs3() {
        // Arrange
        val underTest = ZeroAndPositiveNumberViewController(5)

        // Act
        underTest.decrease(2)

        // Assert
        assertThat(underTest.number).isEqualTo(3)
    }

    @Test
    fun decrease_decrease0By2_resultIs0() {
        // Arrange
        val underTest = ZeroAndPositiveNumberViewController(0)

        // Act
        underTest.decrease(2)

        // Assert
        assertThat(underTest.number).isEqualTo(0)
    }

}