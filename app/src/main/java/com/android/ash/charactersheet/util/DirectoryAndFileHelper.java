package com.android.ash.charactersheet.util;

import java.io.File;

import android.os.Environment;

/**
 * Provides utility methods to handle directories and files.
 */
public class DirectoryAndFileHelper {

    /**
     * Returns the directory to store backup files in.
     * 
     * @return The directory to store backup files in.
     */
    public static File getBackupDirectory() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    /**
     * Returns the directory to store export files in.
     * 
     * @return The directory to store export files in.
     */
    public static File getExportDirectory() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

}
