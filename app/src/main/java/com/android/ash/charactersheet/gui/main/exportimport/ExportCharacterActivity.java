package com.android.ash.charactersheet.gui.main.exportimport;

import static com.d20charactersheet.framework.boc.service.ExportImportService.EXPORT_CHARACTER_FILE_PREFIX;

import java.io.File;
import java.util.List;

import android.widget.ListView;
import android.widget.Toast;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.ExportImportService;

/**
 * Allows to export characters to xml. Displays the export directory. The the list of all characters with checkboxes to
 * select them. The export button starts the export of all selected characters.
 */
public class ExportCharacterActivity extends AbstractExportActivity {

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
                R.layout.listitem_export, new ListModel<Character>(characters));

        final ListView listView = getListView();
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
    }

    private ListView getListView() {
        final ListView listView = (ListView) findViewById(android.R.id.list);
        return listView;
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

}
