package com.android.ash.charactersheet.boc.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.dac.dao.dummy.DummyImageDao
import com.d20charactersheet.framework.boc.service.ImageService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidImageServiceInstrumentedTest {

    private lateinit var imageService: AndroidImageService

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        imageService = AndroidImageServiceImpl(DummyImageDao(context))
    }

    @Test
    fun getImage_defaultCharacterImage_retrievesDefaultCharacterImage() {
        // Act
        val image = imageService.getImage(ImageService.DEFAULT_CHARACTER_IMAGE_ID)

        // Assert
        assertThat(image).isNotNull()
    }

    @Test
    fun getImage_defaultThumbnailImage_retrievesDefaultThumbnailImage() {
        // Act
        val thumbnail = imageService.getImage(ImageService.DEFAULT_THUMB_IMAGE_ID)

        // Assert
        assertThat(thumbnail).isNotNull()
    }

    @Test
    fun getBitmap_defaultCharacterBitmap_retrieveDefaultCharacterBitmap() {
        // Act
        val bitmap = imageService.getBitmap(ImageService.DEFAULT_CHARACTER_IMAGE_ID)

        // Assert
        assertThat(bitmap).isNotNull()
    }

    @Test
    fun getBitmap_notExistingBitmap_retrieveDefaultThumbnailBitmap() {
        // Act
        val bitmap = imageService.getBitmap(-1)

        // Assert
        assertThat(bitmap).isNotNull()
    }

    @Test(expected = IllegalArgumentException::class)
    fun createImage_withNullParameter_throwsIllegalArgumentException() {
        // Act
        imageService.createImage(null as String?)
    }

    @Test
    fun deleteImage_defaultCharacterImage_doNotDeleteDefaultCharacterImage() {
        // Act
        imageService.deleteImage(ImageService.DEFAULT_CHARACTER_IMAGE_ID)

        // Assert
        val image = imageService.getImage(ImageService.DEFAULT_CHARACTER_IMAGE_ID)
        assertThat(image).isNotNull()
    }

    @Test
    fun deleteImage_defaultThumbnailImage_doNotDeleteDefaultThumbnailImage() {
        // Act
        imageService.deleteImage(ImageService.DEFAULT_THUMB_IMAGE_ID)

        // Assert
        val thumbnail = imageService.getImage(ImageService.DEFAULT_THUMB_IMAGE_ID)
        assertThat(thumbnail).isNotNull()
    }

}