package com.android.ash.charactersheet.backuprestore;

import android.content.Context;
import android.os.Environment;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.util.DirectoryAndFileHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Backups a database file to the download directory. Restores a database file from the download directory. The filename
 * is build by the name of the database, the version name of the app and the current date and time.
 */
public class FileBackupAgent {

    static final String SEPARATOR = "_";

    private final Context context;

    /**
     * Creates FileBackupAgent with the given context.
     *
     * @param context The context of the app.
     */
    public FileBackupAgent(final Context context) {
        this.context = context;
    }

    /**
     * Backup of the database of the given game system to the download directory. Checks if the sd card is accessable.
     * Checks the download directory. Checks if enough free space is available. The filename is build by the name of the
     * database, the version name of the app and the current date and time.
     *
     * @param gameSystemType The game system to backup the database.
     * @return The backup file.
     * @throws IOException Thrown if sd card is not accessible, not enough free space or other I/O operation failed.
     */
    public File backup(final GameSystemType gameSystemType) throws IOException {
        if (!isExternalStorageWritable()) {
            throw new IOException("SD Card is not accessible");
        }

        final File backupDirectory = DirectoryAndFileHelper.getBackupDirectory();
        if (!backupDirectory.exists()) {
            if (!backupDirectory.mkdir()) {
                throw new IOException("Missing download directory and can't create it");
            }
        }

        final File srcFile = context.getDatabasePath(gameSystemType.getDatabaseName());
        final File destFile = new File(backupDirectory, getBackupName(gameSystemType.getDatabaseName()));
        copyFile(srcFile, destFile);

        return destFile;
    }

    private void copyFile(final File srcFile, final File destFile) throws IOException {
        Logger.debug("srcFile: " + srcFile);
        Logger.debug("destFile: " + destFile);

        if (!isFreeSpaceAvailable(srcFile)) {
            throw new IOException("Not enough disc space available");
        }

        final FileChannel sourceFileChannel = new FileInputStream(srcFile).getChannel();
        final FileChannel destinationFileChannel = new FileOutputStream(destFile).getChannel();

        final long size = sourceFileChannel.size();

        synchronized (DBHelper.DB_LOCK) {
            sourceFileChannel.transferTo(0, size, destinationFileChannel);
        }
    }

    /**
     * Returns true, if the sd card is accessible.
     *
     * @return True, if the sd card is accessible.
     */
    private boolean isExternalStorageWritable() {
        final String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private boolean isFreeSpaceAvailable(final File srcFile) {
        final long requiredSpace = srcFile.length();
        final long freeSpace = DirectoryAndFileHelper.getBackupDirectory().getFreeSpace();
        Logger.debug("requiredSpace: " + requiredSpace);
        Logger.debug("freeSpace: " + freeSpace);

        return freeSpace >= requiredSpace;
    }

    String getBackupName(final String databaseName) {
        final String versionName = BuildConfig.VERSION_NAME;
        final String pattern = context.getResources().getString(R.string.backup_date_pattern);
        final String date = new SimpleDateFormat(pattern, Locale.US).format(new Date());

        return databaseName + SEPARATOR + versionName + SEPARATOR + date;
    }

    /**
     * Returns all files in the download directory belonging to a game system.
     *
     * @return All files in the download directory belonging to a game system.
     */
    public List<File> getBackupFiles() {
        File[] files = DirectoryAndFileHelper.getBackupDirectory().listFiles(new BackupFilenameFilter());
        if (files == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(files);
    }

    /**
     * Filters backup files. The name of the backup file must start with the database name of a game system.
     */
    static class BackupFilenameFilter implements FilenameFilter {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String databaseName : DBHelper.getDatabaseNames()) {
                if (name.startsWith(databaseName)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Restores a backup file.
     *
     * @param gameSystemType The game system to restore the file of.
     * @param restoreFile    The file to restore.
     * @throws IOException Thrown if the restore failed by an I/O operation.
     */
    public void restore(final GameSystemType gameSystemType, final File restoreFile) throws IOException {
        final File destFile = context.getDatabasePath(gameSystemType.getDatabaseName());
        copyFile(restoreFile, destFile);
    }

}
