package com.android.ash.charactersheet.backuprestore;

import static com.android.ash.charactersheet.dac.dao.android.AndroidPreferenceDao.FILENAME;

import java.io.IOException;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.FileBackupHelper;
import android.app.backup.SharedPreferencesBackupHelper;
import android.os.ParcelFileDescriptor;

import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper;
import com.android.ash.charactersheet.gui.util.Logger;

/**
 * Stores the databases and the preference to the cloud and restores them if the app is installed on a new device. This
 * mechanism must be supported by the provider.
 */
public class CloudBackupAgent extends BackupAgentHelper {

    private static final String BACKUP_KEY_PREFS = "prefs";

    private static final String DATABASE_RELATIVE_PATH = "../databases/";

    @Override
    public void onCreate() {
        Logger.debug("onCreate()");

        // register preferences
        final SharedPreferencesBackupHelper preferencesBackupHelper = new SharedPreferencesBackupHelper(this, FILENAME);
        addHelper(BACKUP_KEY_PREFS, preferencesBackupHelper);

        // register databases
        for (final String databaseName : DBHelper.getDatabaseNames()) {
            addHelper(databaseName, new FileBackupHelper(this, DATABASE_RELATIVE_PATH + databaseName));
        }

    }

    @Override
    public void onBackup(final ParcelFileDescriptor oldState, final BackupDataOutput data,
            final ParcelFileDescriptor newState) throws IOException {
        Logger.debug("onBackup()");
        synchronized (DBHelper.DB_LOCK) {
            super.onBackup(oldState, data, newState);
        }
    }

    @Override
    public void onRestore(final BackupDataInput data, final int appVersionCode, final ParcelFileDescriptor newState)
            throws IOException {
        Logger.debug("onRestore()");
        synchronized (DBHelper.DB_LOCK) {
            super.onRestore(data, appVersionCode, newState);
        }
    }

}
