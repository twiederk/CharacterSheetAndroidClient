package com.android.ash.charactersheet.boc.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.android.ash.charactersheet.dac.dao.dummy.DummyImageDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.d20charactersheet.framework.boc.service.ImageService.DEFAULT_CHARACTER_IMAGE_ID;
import static com.d20charactersheet.framework.boc.service.ImageService.DEFAULT_THUMB_IMAGE_ID;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class AndroidImageServiceTest {

    private AndroidImageService imageService;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        imageService = new AndroidImageServiceImpl(new DummyImageDao(context));
    }

    @Test
    public void testGetImage() {
        final byte[] image = imageService.getImage(DEFAULT_CHARACTER_IMAGE_ID);
        assertNotNull(image);

        final byte[] thumbnail = imageService.getImage(DEFAULT_THUMB_IMAGE_ID);
        assertNotNull(thumbnail);
    }

    @Test
    public void testGetBitmap() {
        final Bitmap bitmap = imageService.getBitmap(DEFAULT_CHARACTER_IMAGE_ID);
        assertNotNull(bitmap);
    }

    @Test
    public void testCreateImage() {
        try {
            imageService.createImage((String) null);
            fail("Missing exception. Expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // OK, expected exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception: " + exception.getClass() + ". Expected IllegalArgumentException");
        }
    }

    @Test
    public void testDeleteImageDontDeleteDefaultImages() {
        imageService.deleteImage(DEFAULT_CHARACTER_IMAGE_ID);
        final byte[] image = imageService.getImage(DEFAULT_CHARACTER_IMAGE_ID);
        assertNotNull(image);

        imageService.deleteImage(DEFAULT_THUMB_IMAGE_ID);
        final byte[] thumbnail = imageService.getImage(DEFAULT_THUMB_IMAGE_ID);
        assertNotNull(thumbnail);
    }

}
