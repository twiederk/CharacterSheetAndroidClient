package com.android.ash.charactersheet.gui.admin.util;

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
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.KnownSpellsTable;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpellsPerDayTable;
import com.d20charactersheet.framework.boc.service.AbilityService;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.SpelllistService;
import com.d20charactersheet.framework.boc.util.AbilityComparator;

import java.util.List;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays all abilities in alphabetical order.
 */
public class AbilitySearchActivity extends LogAppCompatActivity implements OnItemClickListener {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_search);
        setToolbar();

        final GameSystem gameSystem = gameSystemHolder.getValue().getGameSystem();
        createLayout(Objects.requireNonNull(gameSystem));
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.ability_search_title);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }


    private void createLayout(final GameSystem gameSystem) {
        final DisplayService displayService = gameSystem.getDisplayService();
        final AbilityService abilityService = gameSystem.getAbilityService();
        final SpelllistService spelllistService = gameSystem.getSpelllistService();

        final List<Spelllist> allSpelllists = spelllistService.getAllSpelllists(spelllistService.getAllSpells());
        final List<KnownSpellsTable> allKnownSpellsTables = spelllistService.getAllKnownSpellsTables();
        final List<SpellsPerDayTable> allSpellsPerDayTables = spelllistService.getAllSpellsPerDayTables();

        final List<Ability> allAbilities = abilityService.getAllAbilities(allSpelllists, allKnownSpellsTables,
                allSpellsPerDayTables);
        final ArrayAdapter<Ability> adapter = new NameDisplayArrayAdapter<>(this, displayService, allAbilities);
        adapter.sort(new AbilityComparator());
        final ListView listView = findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setTextFilterEnabled(true);

        final EditText searchEditText = findViewById(android.R.id.input);
        searchEditText.addTextChangedListener(new SearchTextWatcher(listView));
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        final Ability ability = (Ability) adapterView.getItemAtPosition(position);
        final Intent resultIntent = new Intent().putExtra(INTENT_EXTRA_DATA_OBJECT, ability.getId());
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
