package com.android.ash.charactersheet.gui.main.promocode

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.random.Random

class PromoCodesTest {

    @Test
    fun getPromoCode() {
        // Arrange

        // Act
        val promoCode = PromoCodes(Random(1L)).getPromoCode()

        // Assert
        assertThat(promoCode).isEqualTo("HC5DDCYGX3PZU8F0J2F00Y6")
    }

}