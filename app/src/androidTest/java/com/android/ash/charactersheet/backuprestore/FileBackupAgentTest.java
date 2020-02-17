package com.android.ash.charactersheet.backuprestore;

import android.content.Context;
import android.os.Environment;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.util.DirectoryAndFileHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static com.android.ash.charactersheet.backuprestore.FileBackupAgent.SEPARATOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class FileBackupAgentTest {

    private Context context;
    private FileBackupAgent fileBackupAgent;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        fileBackupAgent = new FileBackupAgent(context);
    }

    @Test
    public void testBackup() {
        deleteOldBackup();

        try {
            fileBackupAgent.backup(GameSystemType.DNDV35);
            assertBackup(GameSystemType.DNDV35.getDatabaseName());
        } catch (final Exception exception) {
            fail(exception.getMessage());
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
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

    @Test
    public void testGetBackupName() {
        // Arrange
        final String databaseName = GameSystemType.DNDV35.getDatabaseName();
        final String pattern = context.getResources().getString(R.string.backup_date_pattern);
        final String date = new SimpleDateFormat(pattern, Locale.US).format(new Date());

        // Act
        final String backupName = fileBackupAgent.getBackupName(databaseName);

        // Assert
        String expectedName = databaseName + SEPARATOR +
                context.getResources().getString(R.string.app_version_name) + SEPARATOR +
                date;
        assertEquals(expectedName, backupName);
    }

    @Test
    public void testGetBackupDirectory() {
        final File backupDirectory = DirectoryAndFileHelper.getBackupDirectory();
        assertTrue(backupDirectory.getPath().endsWith("ownload"));
    }
}
