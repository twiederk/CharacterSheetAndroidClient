package com.android.ash.charactersheet.backuprestore

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.BuildConfig
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.util.DirectoryAndFileHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.nio.file.Files
import java.text.SimpleDateFormat
import java.util.*

@RunWith(AndroidJUnit4::class)
class FileBackupAgentInstrumentedTest {

    private lateinit var context: Context
    private lateinit var fileBackupAgent: FileBackupAgent

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        fileBackupAgent = FileBackupAgent(context)
    }

    @Test
    @Throws(IOException::class)
    fun testBackup() {
        // Act
        val backupFile = fileBackupAgent.backup(GameSystemType.DND5E)

        // Assert
        assertThat(backupFile).exists()

        // tear down
        Files.delete(backupFile.toPath())
    }

    @Test
    fun testGetBackupName() {
        // Arrange
        val databaseName = GameSystemType.DNDV35.databaseName
        val pattern = context.resources.getString(R.string.backup_date_pattern)
        val date = SimpleDateFormat(pattern, Locale.US).format(Date())

        // Act
        val backupName = fileBackupAgent.getBackupName(databaseName)

        // Assert
        val expectedName = databaseName + FileBackupAgent.SEPARATOR + BuildConfig.VERSION_NAME + FileBackupAgent.SEPARATOR + date
        assertThat(backupName).isEqualTo(expectedName)
    }

    @Test
    fun testGetBackupDirectory() {
        // Act
        val backupDirectory = DirectoryAndFileHelper.getBackupDirectory()

        // Assert
        assertThat(backupDirectory.path).endsWith("Download")
    }
}