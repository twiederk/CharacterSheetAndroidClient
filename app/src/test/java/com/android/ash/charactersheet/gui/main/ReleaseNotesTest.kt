package com.android.ash.charactersheet.gui.main

import android.content.res.Resources
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ReleaseNotesTest {

    @Test
    fun getReleaseNotes_releaseWithVersionCode50_returnsReleaseNotes() {
        // Arrange
        val resources: Resources = mock()
        whenever(resources.getString(any())).doReturn("myReleaseNote\n")

        // Act
        val releaseNotes = ReleaseNotes(resources).getReleaseNotes(53)

        // Assert
        assertThat(releaseNotes).isEqualTo("myReleaseNote\nmyReleaseNote\n")

    }

}