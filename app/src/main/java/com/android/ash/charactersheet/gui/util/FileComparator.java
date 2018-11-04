package com.android.ash.charactersheet.gui.util;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares files by name.
 */
public class FileComparator implements Comparator<File>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(final File file1, final File file2) {
        return file1.getName().compareTo(file2.getName());
    }

}
