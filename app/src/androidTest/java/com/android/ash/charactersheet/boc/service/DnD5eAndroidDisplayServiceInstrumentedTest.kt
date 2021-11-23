package com.android.ash.charactersheet.boc.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.d20charactersheet.framework.boc.model.Save
import com.d20charactersheet.framework.boc.service.DisplayService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class DnD5eAndroidDisplayServiceInstrumentedTest {

    private lateinit var displayService: DisplayService

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        displayService = DnD5eAndroidDisplayService(context.resources)
    }

    @Test
    fun getDisplaySave_strengthSave_returnStrengthSave() {

        // act
        val save = displayService.getDisplaySave(Save.STRENGTH)

        // assert
        assertThat(save).isEqualTo("Strength")
    }

    @Test
    fun getDisplaySave_strengthAndDexteritySave_returnListOfSaves() {

        // arrange
        val saves = EnumSet.of(Save.STRENGTH, Save.DEXTERITY)

        // act
        val save = displayService.getDisplaySaves(saves)

        // assert
        assertThat(save).isEqualTo("Strength, Dexterity")
    }

}