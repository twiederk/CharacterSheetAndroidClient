package com.android.ash.charactersheet.gui.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;

/**
 * The administration offers lists of entities to administer, like character classes, races and abilities. These lists
 * share the same basic functionality, like alphabetically listed entities, create and update entities. Derive from
 * this class to create list of different entities, sharing these basic functionality.
 * 
 * @param <T>
 *            The entity to administer, like character class, race or ability.
 */
public abstract class AdministrationListActivity<T> extends LogAppCompatActivity implements OnItemClickListener {

    protected GameSystem gameSystem;
    protected DisplayService displayService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        setTitle(getTitleResource());
        setToolbar();

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        displayService = gameSystem.getDisplayService();

        final EditText searchEditText = findViewById(android.R.id.input);
        searchEditText.addTextChangedListener(new SearchTextWatcher(getListView()));
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    protected int getLayoutResource() {
        return R.layout.administration_list;
    }

    protected abstract int getTitleResource();

    @Override
    protected void onResume() {
        super.onResume();
        final ListView listView = getListView();
        listView.setAdapter(getAdapter());
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
        listView.setOnItemClickListener(this);
    }

    protected abstract ArrayAdapter<T> getAdapter();

    /**
     * Contains option to create a new feat.
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_admin_create) {
            startActivity(getCreateIntent());
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    protected abstract Intent getCreateIntent();

    @SuppressWarnings("unchecked")
    @Override
    public void onItemClick(final AdapterView<?> listView, final View view, final int position, final long id) {
        final T item = (T) listView.getItemAtPosition(position);
        startActivity(getEditIntent(item));
    }

    protected abstract Intent getEditIntent(T item);

    protected ListView getListView() {
        return (ListView) findViewById(android.R.id.list);
    }

}
