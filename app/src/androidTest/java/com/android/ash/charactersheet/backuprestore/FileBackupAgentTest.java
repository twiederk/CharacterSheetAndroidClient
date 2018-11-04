package com.android.ash.charactersheet.backuprestore;

import static com.android.ash.charactersheet.backuprestore.FileBackupAgent.SEPARATER;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Environment;
import android.test.AndroidTestCase;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.util.DirectoryAndFileHelper;

public class FileBackupAgentTest extends AndroidTestCase {

    private FileBackupAgent fileBackupAgent;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fileBackupAgent = new FileBackupAgent(getContext());
    }

    public void testBackup() {
        deleteOldBackup();

        try {
            fileBackupAgent.backup(GameSystemType.DNDV35);
            assertBackup(GameSystemType.DNDV35.getDatabaseName());
        } catch (final Exception exception) {
            fail(exception.getMessage());
        }
    }

    private void deleteOldBackup() {
        for (final File backupFile : fileBackupAgent.getBackupFiles()) {
            backupFile.delete();
        }
    }

    private void assertBackup(final String databaseName) {
        final File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        final File backupFile = new File(downloadDir, databaseName);
        assertTrue("Missing backup file: " + backupFile, backupFile.getName().startsWith(databaseName));
    }

    public void testGetBackupName() throws Exception {
        final String databaseName = GameSystemType.DNDV35.getDatabaseName();
        final String pattern = getContext().getResources().getString(R.string.backup_date_pattern);
        final String date = new SimpleDateFormat(pattern, Locale.US).format(new Date());

        final StringBuilder expectedName = new StringBuilder();
        expectedName.append(databaseName).append(SEPARATER);
        expectedName.append(getContext().getResources().getString(R.string.app_version_name)).append(SEPARATER);
        expectedName.append(date);

        final String backupName = fileBackupAgent.getBackupName(databaseName);

        assertEquals(expectedName.toString(), backupName);
    }

    public void testGetBackupDirectory() throws Exception {
        final File backupDirectoy = DirectoryAndFileHelper.getBackupDirectory();
        assertTrue(backupDirectoy.getPath().endsWith("ownload"));
    }
}
