package com.android.ash.charactersheet.gui.main.exportimport;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.ash.charactersheet.util.DirectoryAndFileHelper;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static com.d20charactersheet.framework.boc.service.ExportImportService.EXPORT_FILE_SUFFIX;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * Base class for export activities. Displays export directory. Provides template pattern to allow export of different
 * classes.
 */
public abstract class AbstractExportActivity extends LogAppCompatActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    private static final String SEPARATOR = "_";
    private static final String COLON = ": ";

    GameSystem gameSystem;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameSystem = gameSystemHolder.getValue().getGameSystem();

        createLayout();

    }

    private void createLayout() {
        setContentView(getLayoutId());
        setToolbar();

        createExportDirectoryTextView();
        createExportView();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getTitleId());
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    private void createExportDirectoryTextView() {

        final TextView textView = findViewById(R.id.export_export_directory);
        String text = getResources().getString(R.string.export_export_directory) + " " +
                DirectoryAndFileHelper.getExportDirectory().getPath();
        textView.setText(text);
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

        String filename = exportFilePrefix + SEPARATOR + date + EXPORT_FILE_SUFFIX;
        return new File(DirectoryAndFileHelper.getExportDirectory(), filename);
    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_abstract_export, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_activity_abstract_export_export) {
            export();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    abstract int getLayoutId();

    abstract int getTitleId();

    abstract void createExportView();

    abstract void export();
}
