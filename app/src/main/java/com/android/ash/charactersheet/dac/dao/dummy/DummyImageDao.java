package com.android.ash.charactersheet.dac.dao.dummy;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.dac.dao.ImageDao;

/**
 * Dummy implementation of the ImageDao interface to simulate a persistence layer in memory.
 */
public class DummyImageDao implements ImageDao {

    private static int imageCounter;

    private final Context context;
    private SparseArray<byte[]> imageTable;

    private final int[] imageResourceIds = { // image resource id, face resource id
    R.drawable.char_default, R.drawable.char_default_face, // Default
            R.drawable.char_belvador, R.drawable.char_belvador_face,// Belvador
    };

    /**
     * Instanciates ImageDaoDummy with given context.
     *
     * @param context
     *            The context the resources are retrieved from.
     */
    public DummyImageDao(final Context context) {
        checkContext(context);
        this.context = context;
        initImageMap();
    }

    private void initImageMap() {
        imageTable = new SparseArray<byte[]>(imageResourceIds.length);
        for (imageCounter = 0; imageCounter < imageResourceIds.length; imageCounter++) {
            final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageResourceIds[imageCounter]);
            final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            imageTable.put(imageCounter, bytes.toByteArray());
        }
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ImageDao#getImage(int)
     */
    @Override
    public byte[] getImage(final int imageId) {
        checkContext(context);
        byte[] image = imageTable.get(imageId);
        if (image != null) {
            image = image.clone();
        }
        return image;
    }

    private void checkContext(final Context context) {
        if (context == null) {
            throw new IllegalStateException("context is null");
        }
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ImageDao#createImage(byte[])
     */
    @Override
    public int createImage(final byte[] imageData) {
        synchronized (imageTable) {
            final int imageId = imageCounter++;
            imageTable.put(imageId, imageData.clone());
            return imageId;
        }
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ImageDao#deleteImage(int)
     */
    @Override
    public void deleteImage(final int imageId) {
        imageTable.remove(imageId);
    }

}
