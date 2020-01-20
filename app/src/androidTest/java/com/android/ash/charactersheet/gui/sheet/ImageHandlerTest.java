package com.android.ash.charactersheet.gui.sheet;

import android.content.Context;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ImageHandlerTest {

    private ImageHandler imageHandler;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        imageHandler = new ImageHandler(context);
    }

    // file:///mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/Weiterempfohlen/Belvador%20(3).jpg
    // content://media/external/images/media/4380
    @Test
    public void testGetImageFilename() {
        final Uri uri = Uri
                .parse("file:///mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/Weiterempfohlen/Belvador%20(3).jpg");
        final String filename = imageHandler.getImageFilename(uri);
        assertNotNull(filename);
        assertEquals("/mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/Weiterempfohlen/Belvador (3).jpg",
                filename);
    }
}
