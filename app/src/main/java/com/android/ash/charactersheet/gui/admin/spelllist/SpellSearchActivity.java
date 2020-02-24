package com.android.ash.charactersheet.gui.admin.spelllist;

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
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.ash.charactersheet.gui.util.SearchTextWatcher;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.util.SpellComparator;

import java.util.List;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays all spells and allows to search them and select one.
 */
public class SpellSearchActivity extends LogAppCompatActivity implements OnItemClickListener {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final GameSystem gameSystem = gameSystemHolder.getValue().getGameSystem();

        createLayout(Objects.requireNonNull(gameSystem));
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.spell_search_title);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    private void createLayout(final GameSystem gameSystem) {
        setContentView(R.layout.activity_spell_search);
        setTitle(R.string.spell_search_title);
        final List<Spell> allSpells = gameSystem.getAllSpells();
        final ArrayAdapter<Spell> adapter = new NameDisplayArrayAdapter<>(this, gameSystem.getDisplayService(),
                allSpells);
        adapter.sort(new SpellComparator());

        final ListView listView = findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(this);

        final EditText searchEditText = findViewById(android.R.id.input);
        searchEditText.addTextChangedListener(new SearchTextWatcher(listView));
    }

    @Override
    public void onItemClick(final AdapterView<?> listView, final View view, final int position, final long id) {
        final Spell spell = (Spell) listView.getItemAtPosition(position);
        final Intent resultIntent = new Intent().putExtra(INTENT_EXTRA_DATA_OBJECT, spell.getId());
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
