package com.android.ash.charactersheet.gui.sheet.note

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class NoteCreateActivityRobotronicTest {

    @Test
    fun createForm() {
        // Arrange

        // Act
        val note = NoteCreateActivity().createForm()

        // Assert
        assertThat(note.date).isNotNull
        assertThat(note.text).isEmpty()
    }

}