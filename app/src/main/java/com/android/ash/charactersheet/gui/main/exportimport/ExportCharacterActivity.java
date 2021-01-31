package com.android.ash.charactersheet.gui.main.exportimport;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.ExportImportService;

import java.io.File;
import java.util.List;

import static com.d20charactersheet.framework.boc.service.ExportImportService.EXPORT_CHARACTER_FILE_PREFIX;

/**
 * Allows to export characters to xml. Displays the export directory. The the list of all characters with checkboxes to
 * select them. The export button starts the export of all selected characters.
 */
public class ExportCharacterActivity extends AbstractExportActivity {

    private static final int PERMISSION_EXTERNAL_STORAGE = 100;

    @Override
    int getLayoutId() {
        return R.layout.activity_export_character;
    }

    @Override
    int getTitleId() {
        return R.string.export_character_title;
    }

    @Override
    void createExportView() {
        final List<Character> characters = gameSystem.getAllCharacters();
        final ExportCharacterAdapter adapter = new ExportCharacterAdapter(this, gameSystem.getDisplayService(),
                R.layout.listitem_export, new ListModel<>(characters));

        final ListView listView = getListView();
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
    }

    private ListView getListView() {
        return findViewById(android.R.id.list);
    }

    @Override
    void export() {
        Logger.debug("export");
        final ExportCharacterAdapter adapter = (ExportCharacterAdapter) getListView().getAdapter();
        final List<Character> characters = adapter.getSelectedCharacters();

        Logger.debug("characters: " + characters);

        if (characters.isEmpty()) {
            final String message = getResources().getString(R.string.export_character_message_select_character);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            return;
        }

        // export
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_EXTERNAL_STORAGE);
        } else {
            exportCharacters();
        }
    }

    private void exportCharacters() {
        final ExportCharacterAdapter adapter = (ExportCharacterAdapter) getListView().getAdapter();
        final List<Character> characters = adapter.getSelectedCharacters();

        final File exportFile = getExportFile(EXPORT_CHARACTER_FILE_PREFIX);
        final ExportImportService exportImportService = gameSystem.getExportImportService();
        try {
            exportImportService.exportCharacters(gameSystem, exportFile, characters);
            displayMessage(R.string.export_message_export_succeed, exportFile.toString());
        } catch (final Exception exception) {
            Logger.error("Can't export characters", exception);
            displayMessage(R.string.export_message_export_failed, exception.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                exportCharacters();
            }
        }
    }

}
