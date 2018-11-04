package com.android.ash.charactersheet.gui.admin.spelllist;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.android.ash.charactersheet.gui.util.SearchTextWatcher;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.util.SpellComparator;

/**
 * Displays all spells and allows to search them and select one.
 */
public class SpellSearchActivity extends LogActivity implements OnItemClickListener {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        final GameSystem gameSystem = application.getGameSystem();

        createLayout(gameSystem);
    }

    private void createLayout(final GameSystem gameSystem) {
        setContentView(R.layout.activity_spell_search);
        setTitle(R.string.spell_search_title);
        final List<Spell> allSpells = gameSystem.getAllSpells();
        final ArrayAdapter<Spell> adapter = new NameDisplayArrayAdapter<Spell>(this, gameSystem.getDisplayService(),
                allSpells);
        adapter.sort(new SpellComparator());

        final ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(this);

        final EditText searchEditText = (EditText) findViewById(android.R.id.input);
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
