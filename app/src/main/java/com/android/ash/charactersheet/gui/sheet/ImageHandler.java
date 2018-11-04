package com.android.ash.charactersheet.gui.sheet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.MediaColumns;

/**
 * Utility class to handle images in Android specific manner.
 */
public class ImageHandler {

    private static final String SCHEME_FILE = "file";

    private static final int THUMBNAIL_WIDTH = 48;
    private static final int THUMBNAIL_HEIGHT = 48;

    private final Context context;

    private final float scale;

    /**
     * Intanciates ImageHandler with given ContentResolver.
     * 
     * @param context
     *            The context to access Android images.
     */
    public ImageHandler(final Context context) {
        this.context = context;
        scale = context.getResources().getDisplayMetrics().density;
    }

    /**
     * Returns the name to the physical file of the given uri.
     * 
     * @param intent
     *            The intent containing the uri of the image.
     * @return The filename of the image.
     */
    public String getFilename(final Intent intent) {
        final Uri uri = getImageUri(intent);
        final String filename = getImageFilename(uri);
        return filename;
    }

    /**
     * Returns the cropped image of the CropImage activty.
     * 
     * @param intent
     *            The result of the CropImage activity.
     * @return The uri of the cropped image.
     */
    public Uri getImageUri(final Intent intent) {
        Uri croppedImage;
        final String action = intent.getAction();
        if (action != null) {
            croppedImage = Uri.parse(action);
        } else {
            croppedImage = intent.getData();
        }
        return croppedImage;
    }

    String getImageFilename(final Uri uri) {
        final String scheme = uri.getScheme();
        String filename;
        if (scheme.startsWith(SCHEME_FILE)) {
            filename = uri.getPath();

        } else {
            final String[] projection = { MediaColumns.DATA };
            final Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();
            final int columnIndex = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
            filename = cursor.getString(columnIndex);
            cursor.close();

        }
        return filename;
    }

    /**
     * Creates an intent to call the CropImage activity. The intent is configured with the given parameters. Face
     * detection is diabled. The image is scaled to the given width and height.
     * 
     * @param selectedImage
     *            The image to crop.
     * @return The intent to call the CropImage activity with the given configuration.
     */
    public Intent configureCropThumbnail(final Uri selectedImage) {
        final Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setData(selectedImage);

        final int width = (int) (THUMBNAIL_WIDTH * scale);
        final int height = (int) (THUMBNAIL_HEIGHT * scale);

        // configure crop thumbnail
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("aspectX", width);
        intent.putExtra("aspectY", height);
        intent.putExtra("scale", true);
        return intent;
    }

    /**
     * Creates intent to call crop action. The image to crop is containted in the intent and face detection is disabled.
     * 
     * @param selectedImage
     *            The image to crop.
     * @return The intent to call the crop action.
     */
    public Intent configureCropImage(final Uri selectedImage) {
        final Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setData(selectedImage);

        // configure crop image
        intent.putExtra("noFaceDetection", true);

        return intent;
    }

}
