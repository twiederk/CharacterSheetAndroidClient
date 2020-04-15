package com.android.ash.charactersheet.gui.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;


/**
 * The administration offers lists of entities to administer, like character classes, races and abilities. These lists
 * share the same basic functionality, like alphabetically listed entities, create and update entities. Derive from
 * this class to create list of different entities, sharing these basic functionality.
 *
 * @param <T> The entity to administer, like character class, race or ability.
 */
public abstract class AdministrationListActivity<T> extends LogAppCompatActivity implements OnItemClickListener {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    protected GameSystem gameSystem;
    protected DisplayService displayService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        setToolbar();
        setFavoriteActionButton();

        gameSystem = gameSystemHolder.getValue().getGameSystem();
        displayService = Objects.requireNonNull(gameSystem).getDisplayService();

        final EditText searchEditText = findViewById(android.R.id.input);
        searchEditText.addTextChangedListener(new SearchTextWatcher(getListView()));
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getTitleResource());
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    private void setFavoriteActionButton() {
        findViewById(R.id.favorite_action_button).setOnClickListener(view -> startActivity(getCreateIntent()));
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
