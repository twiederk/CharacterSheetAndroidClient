package com.android.ash.charactersheet.gui.sheet.attribute

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AttributeNumberViewControllerTest {

    @Test
    fun decrease_decreaseFrom2_resultIs1() {
        // Arrange
        val underTest = AttributeNumberViewController(AttributeModel(2))

        // Act
        underTest.decrease()

        // Assert
        assertThat(underTest.number).isEqualTo(1)
    }

    @Test
    fun decrease_decreaseFrom1_resultIs1() {
        // Arrange
        val underTest = AttributeNumberViewController(AttributeModel(1))

        // Act
        underTest.decrease()

        // Assert
        assertThat(underTest.number).isEqualTo(1)
    }

    @Test
    fun decrease_decrease3From5_resultIs2() {
        // Arrange
        val underTest = AttributeNumberViewController(AttributeModel(5))

        // Act
        underTest.decrease(3)

        // Assert
        assertThat(underTest.number).isEqualTo(2)
    }

    @Test
    fun decrease_decrease3From2_resultIs1() {
        // Arrange
        val underTest = AttributeNumberViewController(AttributeModel(2))

        // Act
        underTest.decrease(3)

        // Assert
        assertThat(underTest.number).isEqualTo(1)
    }

    @Test
    fun increase_increaseFrom10_resultIs11() {
        // Arrange
        val underTest = AttributeNumberViewController(AttributeModel(10))

        // Act
        underTest.increase()

        // Assert
        assertThat(underTest.number).isEqualTo(11)
    }

    @Test
    fun increase_increaseFrom99_resultIs99() {
        // Arrange
        val underTest = AttributeNumberViewController(AttributeModel(99))

        // Act
        underTest.increase()

        // Assert
        assertThat(underTest.number).isEqualTo(99)
    }

    @Test
    fun increase_increase5From10_resultIs15() {
        // Arrange
        val underTest = AttributeNumberViewController(AttributeModel(10))

        // Act
        underTest.increase(5)

        // Assert
        assertThat(underTest.number).isEqualTo(15)
    }

    @Test
    fun increase_increase10From95_resultIs99() {
        // Arrange
        val underTest = AttributeNumberViewController(AttributeModel(95))

        // Act
        underTest.increase(10)

        // Assert
        assertThat(underTest.number).isEqualTo(99)
    }

}