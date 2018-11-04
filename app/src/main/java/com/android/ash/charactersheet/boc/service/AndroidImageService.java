package com.android.ash.charactersheet.boc.service;

import android.graphics.Bitmap;

import com.d20charactersheet.framework.boc.service.ImageService;


/**
 * The AndroidImageService interface extends the ImageService with Android specific methods.
 */
public interface AndroidImageService extends ImageService {

    /**
     * Returns Bitmap of the given image id.
     * 
     * @param imageId
     *            The id of the image.
     * @return The bitmap of the given id.
     */
    public Bitmap getBitmap(int imageId);
}
