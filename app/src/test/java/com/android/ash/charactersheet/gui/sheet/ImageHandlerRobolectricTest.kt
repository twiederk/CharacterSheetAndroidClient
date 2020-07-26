package com.android.ash.charactersheet.gui.sheet

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class ImageHandlerRobolectricTest {
    // file:///mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/recommended/Belvador%20(3).jpg
    // content://media/external/images/media/4380
    @Test
    fun testGetImageFilename() {

        // Arrange
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val imageHandler = ImageHandler(context)
        val uri = Uri.parse("file:///mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/recommended/Belvador%20(3).jpg")

        // Act
        val filename = imageHandler.getImageFilename(uri)

        // Assert
        assertThat(filename).isEqualTo("/mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/recommended/Belvador (3).jpg")
    }
}