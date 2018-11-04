package com.android.ash.charactersheet.boc.service;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.d20charactersheet.framework.dac.dao.ImageDao;

/**
 * Implements Android OS specific class of ImageService.
 */
public class AndroidImageServiceImpl implements AndroidImageService {

    private final ImageDao imageDao;

    /**
     * Creates ImagesService with data asses.
     * 
     * @param imageDao
     *            The data access.
     */
    public AndroidImageServiceImpl(final ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.ImageService#getImage(int)
     */
    @Override
    public byte[] getImage(final int imageId) {
        return imageDao.getImage(imageId);
    }

    /**
     * @see com.android.ash.charactersheet.boc.service.AndroidImageService#getBitmap(int)
     */
    @Override
    public Bitmap getBitmap(final int imageId) {
        final byte[] imageData = imageDao.getImage(imageId);
        final Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        return bitmap;
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.ImageService#createImage(java.lang.String)
     */
    @Override
    public int createImage(final String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("filename is null");
        }
        final byte[] imageData = getImageData(filename);
        final int imageId = imageDao.createImage(imageData);
        return imageId;
    }

    private byte[] getImageData(final String filename) {
        final Bitmap bitmap = BitmapFactory.decodeFile(filename);
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        return bytes.toByteArray();
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.ImageService#deleteImage(int)
     */
    @Override
    public void deleteImage(final int imageId) {
        if (imageId == DEFAULT_CHARACTER_IMAGE_ID || imageId == DEFAULT_THUMB_IMAGE_ID) {
            return;
        }
        imageDao.deleteImage(imageId);
    }

}
