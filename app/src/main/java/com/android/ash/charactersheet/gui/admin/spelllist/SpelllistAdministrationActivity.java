package com.android.ash.charactersheet.gui.admin.spelllist;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormularActivity;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndPositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Displays a spell list with name, short name and spell assignments. Allows to create a new spell assignment.
 */
public abstract class SpelllistAdministrationActivity extends FormularActivity<Spelllist> {

    private static final int REQUEST_CODE_SPELL_SEARCH = 100;
    GameSystem gameSystem;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();

        super.onCreate(savedInstanceState, R.layout.spelllist_administration);
    }

    @Override
    protected void fillViews() {
        setText(form.getName(), R.id.spelllist_administration_name);
        setText(form.getShortname(), R.id.spelllist_administration_short_name);
        setCheckBox(form.isDomain(), R.id.spelllist_administration_domain);
        setNumberViewController(new ZeroAndPositiveNumberViewController(form.getMinLevel()),
                R.id.spelllist_administration_min_level);
        setNumberViewController(new ZeroAndPositiveNumberViewController(form.getMaxLevel()),
                R.id.spelllist_administration_max_level);
        setListView();
    }

    private void setListView() {
        final ListView listView = getListView();
        listView.setAdapter(getAdapter());
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
    }

    private ListAdapter getAdapter() {
        final ListModel<SpelllevelView> viewModel = new SpelllistViewModel(form);
        final ListAdapter adapter = new SpelllevelAdapter(this, gameSystem, viewModel);
        return adapter;
    }

    protected ListView getListView() {
        return (ListView) findViewById(android.R.id.list);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_spelllist_administration, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_activity_spelllist_administration_add_spell:
            fillForm();
            startActivityForResult(new Intent(this, SpellSearchActivity.class), REQUEST_CODE_SPELL_SEARCH);
            break;

        default:
            return super.onMenuItemSelected(featureId, item);
        }
        return true;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {

        // see which child activity is calling us back.
        switch (requestCode) {
        case REQUEST_CODE_SPELL_SEARCH:
            if (resultCode != RESULT_CANCELED) {
                final int spellId = resultIntent.getExtras().getInt(INTENT_EXTRA_DATA_OBJECT);
                final Spell spell = gameSystem.getSpelllistService().findSpellById(spellId, gameSystem.getAllSpells());
                gameSystem.getSpelllistService().createSpelllevel(form, spell, 1);
            }
            break;

        default:
            throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
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
