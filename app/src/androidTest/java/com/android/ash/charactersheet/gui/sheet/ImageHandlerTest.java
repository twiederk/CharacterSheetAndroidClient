package com.android.ash.charactersheet.gui.sheet;

import android.net.Uri;
import android.test.AndroidTestCase;

public class ImageHandlerTest extends AndroidTestCase {

    private ImageHandler imageHandler;

    @Override
    protected void setUp() throws Exception {
        imageHandler = new ImageHandler(getContext());
    }

    // file:///mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/Weiterempfohlen/Belvador%20(3).jpg
    // content://media/external/images/media/4380
    public void testGetImageFilename() {
        final Uri uri = Uri
                .parse("file:///mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/Weiterempfohlen/Belvador%20(3).jpg");
        final String filename = imageHandler.getImageFilename(uri);
        assertNotNull(filename);
        assertEquals("/mnt/sdcard/Android/data/com.amazon.photos/files/Pictures/Weiterempfohlen/Belvador (3).jpg",
                filename);
    }
}
