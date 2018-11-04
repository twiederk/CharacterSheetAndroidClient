package com.android.ash.charactersheet.boc.service;

import android.graphics.Bitmap;
import android.test.AndroidTestCase;

import com.android.ash.charactersheet.dac.dao.dummy.DummyImageDao;

public class AndroidImageServiceTest extends AndroidTestCase {

    private AndroidImageService imageService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        imageService = new AndroidImageServiceImpl(new DummyImageDao(getContext()));
    }

    public void testGetImage() {
        final byte[] image = imageService.getImage(DEFAULT_CHARACTER_IMAGE_ID);
        assertNotNull(image);

        final byte[] thumbnail = imageService.getImage(DEFAULT_THUMB_IMAGE_ID);
        assertNotNull(thumbnail);
    }

    public void testGetBitmap() throws Exception {
        final Bitmap bitmap = imageService.getBitmap(DEFAULT_CHARACTER_IMAGE_ID);
        assertNotNull(bitmap);
    }

    public void testCreateImage() {
        try {
            imageService.createImage(null);
            fail("Missing exception. Expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // OK, expected exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception: " + exception.getClass() + ". Expected IllegalArgumentException");
        }
    }

    public void testDeleteImageDontDeleteDefaultImages() {
        imageService.deleteImage(DEFAULT_CHARACTER_IMAGE_ID);
        final byte[] image = imageService.getImage(DEFAULT_CHARACTER_IMAGE_ID);
        assertNotNull(image);

        imageService.deleteImage(DEFAULT_THUMB_IMAGE_ID);
        final byte[] thumbnail = imageService.getImage(DEFAULT_THUMB_IMAGE_ID);
        assertNotNull(thumbnail);
    }

}
