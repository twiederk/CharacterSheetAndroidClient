package com.android.ash.charactersheet.gui.main;

import static org.koin.java.KoinJavaComponent.inject;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.backup.BackupManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.backuprestore.FileBackupAgent;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.util.DirectoryAndFileHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import kotlin.Lazy;

/**
 * Allows to backup databases to cloud and file and restore from file. The Backup to Cloud button uses the Android
 * backup mechanism to store the database to the cloud. The button Backup to File stores the select database with
 * version and date to the download directory. The list displays databases located in the download directory. Touching a
 * file restores it.
 */
public class BackupRestoreActivity extends LogAppCompatActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    private static final String LINE_FEED = "\n";
    private static final String COLON = ": ";
    private static final int PERMISSION_EXTERNAL_STORAGE = 100;

    private CheckBox dndv35Checkbox;
    private CheckBox dnd5eCheckbox;
    private CheckBox pathfinderCheckbox;

    private BackupManager backupManager;
    private FileBackupAgent fileBackupAgent;

    private final ActivityResultLauncher<Intent> openDocumentActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        String fileName = null;
                        GameSystemType gameSystemType = null;
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            try {
                                Intent intent = result.getData();
                                if (intent == null) {
                                    throw new IllegalStateException("data of intent is null");
                                }
                                Uri uri = intent.getData();

                                fileName = getFileName(uri);
                                gameSystemType = getGameSystemType(fileName);

                                InputStream inputStream = getContentResolver().openInputStream(uri);

                                confirmRestore(gameSystemType, fileName, inputStream);
                            } catch (Exception exception) {
                                Logger.error("Can't restore from file: " + fileName, exception);
                                String gameSystemName = gameSystemType != null ? gameSystemType.getName() : "unknown";
                                final String message = getMessage(gameSystemName, R.string.backup_restore_message_restore_failure,
                                        exception.getMessage());
                                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                            }
                        }
                    });

    private String getFileName(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int displayNameColumnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        String fileName = cursor.getString(displayNameColumnIndex);
        cursor.close();
        return fileName;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backupManager = new BackupManager(this);
        fileBackupAgent = new FileBackupAgent(this);

        createLayout();
    }

    private void createLayout() {
        setContentView(R.layout.activity_backup_restore);
        setToolbar();

        createBackupToCloudLayout();
        createBackupToFileLayout();
        createRestoreFromFileLayout();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.backup_restore_title);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }


    private void createBackupToCloudLayout() {
        createBackupToCloudButton();
    }

    private void createBackupToCloudButton() {
        final Button backupToCloudButton = findViewById(R.id.backup_restore_backup_to_cloud_button);
        backupToCloudButton.setOnClickListener(v -> backupToCloud());
    }

    private void backupToCloud() {
        Logger.debug("backupToCloud()");
        backupManager.dataChanged();
        Toast.makeText(this, R.string.backup_restore_message_backup_to_cloud, Toast.LENGTH_LONG).show();
    }

    private void createBackupToFileLayout() {
        createDirectoryTextView(R.id.backup_restore_backup_directory, R.string.backup_restore_backup_directory);
        createBackupCheckBoxes();
        createBackupToFileButton();
    }

    private void createBackupCheckBoxes() {
        dndv35Checkbox = findViewById(R.id.backup_restore_backup_dndv35);
        dndv35Checkbox.setText(GameSystemType.DNDV35.getName());

        dnd5eCheckbox = findViewById(R.id.backup_restore_backup_dnd5e);
        dnd5eCheckbox.setText(GameSystemType.DND5E.getName());

        pathfinderCheckbox = findViewById(R.id.backup_restore_backup_pathfinder);
        pathfinderCheckbox.setText(GameSystemType.PATHFINDER.getName());

    }

    private void createBackupToFileButton() {
        final Button backupToFileButton = findViewById(R.id.backup_restore_backup_to_file_button);
        backupToFileButton.setOnClickListener(v -> backupToFile());
    }

    private void backupToFile() {
        Logger.debug("backupToFile()");
        final List<String> messages = new ArrayList<>(GameSystemType.values().length);
        backupToFile(GameSystemType.DNDV35, dndv35Checkbox, messages);
        backupToFile(GameSystemType.DND5E, dnd5eCheckbox, messages);
        backupToFile(GameSystemType.PATHFINDER, pathfinderCheckbox, messages);
        Toast.makeText(this, getMessage(messages), Toast.LENGTH_LONG).show();
    }

    private void backupToFile(final GameSystemType gameSystemType, final CheckBox checkbox, final List<String> messages) {
        if (checkbox.isChecked()) {
            try {
                final File backupFile = fileBackupAgent.backup(gameSystemType);
                messages.add(getMessage(gameSystemType.getName(), R.string.backup_restore_message_backup_succeed,
                        backupFile.toString()));
            } catch (final IOException ioException) {
                Logger.error("Can't backup to file", ioException);
                messages.add(getMessage(gameSystemType.getName(), R.string.backup_restore_message_backup_failure,
                        ioException.getMessage()));
            }
        }
    }

    private String getMessage(final String gameSystemName, final int resourceId, final String text) {
        return gameSystemName + COLON + getResources().getString(resourceId) + COLON + text;
    }

    private String getMessage(final List<String> messages) {
        if (messages.isEmpty()) {
            return getResources().getString(R.string.backup_restore_message_select_checkbox);
        }

        final StringBuilder message = new StringBuilder();
        for (final Iterator<String> iterator = messages.iterator(); iterator.hasNext(); ) {
            final String currentMessage = iterator.next();
            message.append(currentMessage);
            if (iterator.hasNext()) {
                message.append(LINE_FEED);
            }
        }
        return message.toString();
    }

    private void createRestoreFromFileLayout() {
        createDirectoryTextView(R.id.backup_restore_restore_directory, R.string.backup_restore_restore_directory);
        createRestoreFromFileButton();
    }

    private void createDirectoryTextView(final int textViewId, final int textId) {
        final TextView textView = findViewById(textViewId);
        String text = getResources().getString(textId) + " " + DirectoryAndFileHelper.getBackupDirectory().getPath();
        textView.setText(text);
    }

    private void createRestoreFromFileButton() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_EXTERNAL_STORAGE);
        } else {
            configureRestoreFromFileButton();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                configureRestoreFromFileButton();
            }
        }
    }

    private void configureRestoreFromFileButton() {
        final Button restoreFromFileButton = findViewById(R.id.backup_restore_restore_from_file_button);
        restoreFromFileButton.setOnClickListener(v -> launchIntentForOpenDocumentActivity());
    }

    private void launchIntentForOpenDocumentActivity() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        openDocumentActivityResultLauncher.launch(intent);
    }


    private GameSystemType getGameSystemType(final String fileName) {
        for (final GameSystemType gameSystemType : GameSystemType.values()) {
            if (fileName.startsWith(gameSystemType.getDatabaseName())) {
                return gameSystemType;
            }

        }
        throw new IllegalArgumentException("Can't get game system of backup file: " + fileName);
    }

    private void confirmRestore(final GameSystemType gameSystemType, final String fileName, final InputStream inputStream) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.backup_restore_dialog_title);
        builder.setMessage(getMessage(gameSystemType, fileName));
        builder.setPositiveButton(R.string.backup_restore_dialog_restore_button, (dialog, id) -> restoreFromFile(gameSystemType, inputStream));
        builder.setNegativeButton(R.string.backup_restore_dialog_cancel_button, (dialog, id) -> {
            // user cancelled the dialog
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String getMessage(final GameSystemType gameSystemType, final String fileName) {
        return "Restore " + gameSystemType.getName() + " from file " + fileName;
    }

    private void restoreFromFile(final GameSystemType gameSystemType, final InputStream inputStream) {
        Logger.debug("restoreFromFile");
        Logger.debug("inputStream: " + inputStream);
        try {
            // close database
            final DBHelper dbHelper = getDBHelper(gameSystemType);
            dbHelper.close();

            // restore file
            fileBackupAgent.restore(gameSystemType, inputStream);

            // drop game system to reload it in CharacterListActivity
            gameSystemHolder.getValue().setGameSystem(null);

            // return to CharacterListActivity
            finish();
        } catch (final Exception exception) {
            Logger.error("Can't restore from file: " + inputStream, exception);
            final String message = getMessage(gameSystemType.getName(), R.string.backup_restore_message_restore_failure,
                    exception.getMessage());
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    private DBHelper getDBHelper(final GameSystemType gameSystemType) {
        for (final DBHelper dbHelper : DBHelper.getDBHelpers()) {
            if (dbHelper.getDatabaseName().equals(gameSystemType.getDatabaseName())) {
                return dbHelper;
            }
        }
        throw new IllegalArgumentException("Can't get dbHelper of game system: " + gameSystemType);
    }
}
