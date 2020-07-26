package com.android.ash.charactersheet.gui.sheet.item

import com.d20charactersheet.framework.boc.model.ItemGroup
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ItemGroupNumberViewControllerTest {

    @Test
    fun decrease_decreaseFrom2_resultIs1() {
        // Arrange
        val underTest = ItemGroupNumberViewController(ItemGroup().apply { number = 2 })

        // Act
        underTest.decrease()

        // Assert
        assertThat(underTest.number).isEqualTo(1)
    }

    @Test
    fun decrease_decreaseFrom0_resultIs0() {
        // Arrange
        val underTest = ItemGroupNumberViewController(ItemGroup())

        // Act
        underTest.decrease()

        // Assert
        assertThat(underTest.number).isEqualTo(0)
    }

    @Test
    fun decrease_decrease3From5_resultIs2() {
        // Arrange
        val underTest = ItemGroupNumberViewController(ItemGroup().apply { number = 5 })

        // Act
        underTest.decrease(3)

        // Assert
        assertThat(underTest.number).isEqualTo(2)
    }

    @Test
    fun decrease_decrease3From2_resultIs1() {
        // Arrange
        val underTest = ItemGroupNumberViewController(ItemGroup().apply { number = 2 })

        // Act
        underTest.decrease(3)

        // Assert
        assertThat(underTest.number).isEqualTo(0)
    }

    @Test
    fun increase_increaseFrom10_resultIs11() {
        // Arrange
        val underTest = ItemGroupNumberViewController(ItemGroup().apply { number = 10 })

        // Act
        underTest.increase()

        // Assert
        assertThat(underTest.number).isEqualTo(11)
    }

    @Test
    fun increase_increase5From10_resultIs15() {
        // Arrange
        val underTest = ItemGroupNumberViewController(ItemGroup().apply { number = 10 })

        // Act
        underTest.increase(5)

        // Assert
        assertThat(underTest.number).isEqualTo(15)
    }

}