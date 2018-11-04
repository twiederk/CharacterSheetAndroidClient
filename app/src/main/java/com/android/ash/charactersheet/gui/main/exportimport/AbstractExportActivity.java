package com.android.ash.charactersheet.gui.main.exportimport;

import static com.d20charactersheet.framework.boc.service.ExportImportService.EXPORT_FILE_SUFFIX;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.android.ash.charactersheet.util.DirectoryAndFileHelper;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Base class for export activities. Displays export directory. Provides template pattern to allow export of different
 * classes.
 */
public abstract class AbstractExportActivity extends LogActivity {

    static final String SEPARATER = "_";
    static final String COLON = ": ";

    GameSystem gameSystem;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();

        createLayout();

    }

    private void createLayout() {
        setContentView(getLayoutId());
        setTitle(getTitleId());

        createExportDirectoryTextView();
        createExportView();
    }

    private void createExportDirectoryTextView() {
        final StringBuilder text = new StringBuilder();
        text.append(getResources().getString(R.string.export_export_directory));
        text.append(" ");
        text.append(DirectoryAndFileHelper.getExportDirectory().getPath());

        final TextView textView = (TextView) findViewById(R.id.export_export_directory);
        textView.setText(text.toString());
    }

    void displayMessage(final int resourceId, final String text) {
        final StringBuilder message = new StringBuilder();
        message.append(getResources().getString(resourceId));
        message.append(COLON);
        message.append(text);

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    File getExportFile(final String exportFilePrefix) {
        final String pattern = getResources().getString(R.string.backup_date_pattern);
        final String date = new SimpleDateFormat(pattern, Locale.US).format(new Date());

        final StringBuffer filename = new StringBuffer();
        filename.append(exportFilePrefix);
        filename.append(SEPARATER);
        filename.append(date);
        filename.append(EXPORT_FILE_SUFFIX);

        final File exportFile = new File(DirectoryAndFileHelper.getExportDirectory(), filename.toString());
        return exportFile;
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_abstract_export, menu);
        return true;
    }

    /**
     * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
     */
    @Override
    public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_activity_abstract_export_export:
            export();
            return true;

        default:
            return super.onMenuItemSelected(featureId, item);
        }
    }

    abstract int getLayoutId();

    abstract int getTitleId();

    abstract void createExportView();

    abstract void export();
}
