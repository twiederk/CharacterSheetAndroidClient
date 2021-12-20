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
class DnDv35AndroidDisplayServiceInstrumentedTest {

    private lateinit var displayService: DisplayService

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        displayService = DnDv35AndroidDisplayService(context.resources)
    }

    @Test
    fun getDisplaySave_strengthSave_returnFortitudeSave() {

        // act
        val save = displayService.getDisplaySave(Save.STRENGTH)

        // assert
        assertThat(save).isEqualTo("Fortitude")
    }

    @Test
    fun getDisplaySave_fortitudeAndWillSave_returnListOfSaves() {

        // arrange
        val saves = EnumSet.of(Save.STRENGTH, Save.CONSTITUTION)

        // act
        val save = displayService.getDisplaySaves(saves)

        // assert
        assertThat(save).isEqualTo("Fortitude, Will")
    }

    @Test
    fun getDisplaySave_intelligenceAndWisdomAndCharismaSave_returnListOfSaves() {

        // arrange
        val saves = EnumSet.of(Save.INTELLIGENCE, Save.WISDOM, Save.CHARISMA)

        // act
        val save = displayService.getDisplaySaves(saves)

        // assert
        assertThat(save).isEqualTo("Intelligence, Wisdom, Charisma")
    }

}