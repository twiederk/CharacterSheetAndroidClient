package com.android.ash.charactersheet.gui.sheet.note

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NoteCreateActivityTest {

    @Test
    fun createForm() {
        // Arrange

        // Act
        val note = NoteCreateActivity().createForm()

        // Assert
        assertThat(note.date).isNotNull()
        assertThat(note.text).isEmpty()
    }

}