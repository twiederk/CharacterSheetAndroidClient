package com.android.ash.charactersheet.gui.admin.spelllist;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndPositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.Objects;

import kotlin.Lazy;

/**
 * Displays a spell list with name, short name and spell assignments. Allows to create a new spell assignment.
 */
public abstract class SpelllistAdministrationActivity extends FormActivity<Spelllist> {

    private static final int REQUEST_CODE_SPELL_SEARCH = 100;

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    GameSystem gameSystem;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.spelllist_administration;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
    }

    @Override
    protected void fillViews() {
        setToolbar();
        setText(form.getName(), R.id.spelllist_administration_name);
        setText(form.getShortname(), R.id.spelllist_administration_short_name);
        setCheckBox(form.isDomain(), R.id.spelllist_administration_domain);
        setNumberViewController(new ZeroAndPositiveNumberViewController(form.getMinLevel()),
                R.id.spelllist_administration_min_level);
        setNumberViewController(new ZeroAndPositiveNumberViewController(form.getMaxLevel()),
                R.id.spelllist_administration_max_level);
        setListView();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }


    private void setListView() {
        final ListView listView = getListView();
        listView.setAdapter(getAdapter());
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
    }

    private ListAdapter getAdapter() {
        final ListModel<SpelllevelView> viewModel = new SpelllistViewModel(form);
        return new SpelllevelAdapter(this, gameSystem, viewModel);
    }

    private ListView getListView() {
        return findViewById(android.R.id.list);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_spelllist_administration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_activity_spelllist_administration_add_spell) {
            fillForm();
            startActivityForResult(new Intent(this, SpellSearchActivity.class), REQUEST_CODE_SPELL_SEARCH);
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);

        if (requestCode == REQUEST_CODE_SPELL_SEARCH) {
            if (resultCode != RESULT_CANCELED) {
                final int spellId = Objects.requireNonNull(resultIntent.getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
                addSpell(spellId);
            }
        } else {
            throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }
    }

    private void addSpell(int spellId) {
        final Spell spell = gameSystem.getSpelllistService().findSpellById(spellId, gameSystem.getAllSpells());
        if (!gameSystem.getSpelllistService().createSpelllevel(form, spell, 1)) {
            Toast.makeText(this, getString(R.string.spelllist_administration_add_spell_failure, spell.getName()), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.spelllist_administration_name));
        form.setShortname(getTextOfTextView(R.id.spelllist_administration_short_name));
        form.setDomain(isChecked(R.id.spelllist_administration_domain));
        form.setMinLevel(getIntegerOfNumberView(R.id.spelllist_administration_min_level));
        form.setMaxLevel(getIntegerOfNumberView(R.id.spelllist_administration_max_level));
    }

    @Override
    protected void saveForm() {
        gameSystem.getSpelllistService().updateSpelllist(form);
        gameSystem.clear();
    }

}
