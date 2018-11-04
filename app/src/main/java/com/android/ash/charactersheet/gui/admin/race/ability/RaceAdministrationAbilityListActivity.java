package com.android.ash.charactersheet.gui.admin.race.ability;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.util.AbilitySearchActivity;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.util.AbilityComparator;

/**
 * Displays all abilities with checkboxes. By checking a ability it is associated with the race.
 */
public class RaceAdministrationAbilityListActivity extends LogActivity {

    private static final int CONTEXT_MENU_ABILITY_REMOVE = 1;

    private static final int REQUEST_CODE_SEARCH_ABILITY = 100;

    private GameSystem gameSystem;
    private List<Ability> abilities;
    private ArrayAdapter<Ability> adapter;
    private Ability selectedAbility;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race_administration_abilities);
        setTitle(R.string.race_administration_ability_list_title);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();

        setAbilities();
    }

    private void setAbilities() {
        abilities = getAbilitiesFromIntent();

        adapter = new NameDisplayArrayAdapter<Ability>(this, gameSystem.getDisplayService(), abilities);
        adapter.sort(new AbilityComparator());
        final ListView abilityListView = getListView();
        abilityListView.setOnCreateContextMenuListener(this);
        abilityListView.setAdapter(adapter);
    }

    @SuppressWarnings("unchecked")
    private List<Ability> getAbilitiesFromIntent() {
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        final List<Integer> abilityIds = (List<Integer>) extras.getSerializable(INTENT_EXTRA_DATA_OBJECT);
        final List<Ability> abilities = new ArrayList<Ability>(abilityIds.size());
        for (final Integer abilityId : abilityIds) {
            final Ability ability = gameSystem.getAbilityService().getAbilityById(abilityId,
                    gameSystem.getAllAbilities());
            abilities.add(ability);
        }
        return abilities;
    }

    private ListView getListView() {
        return (ListView) findViewById(android.R.id.list);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            final Intent resultIntent = new Intent();
            final List<Integer> abilityIds = getAbilityIds();
            resultIntent.putExtra(INTENT_EXTRA_DATA_OBJECT, (Serializable) abilityIds);
            setResult(RESULT_OK, resultIntent);
        }
        return super.onKeyDown(keyCode, event);
    }

    private List<Integer> getAbilityIds() {
        final List<Integer> abilityIds = new ArrayList<Integer>(abilities.size());
        for (final Ability ability : abilities) {
            abilityIds.add(ability.getId());
        }
        return abilityIds;
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View view, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        selectedAbility = (Ability) getListView().getAdapter().getItem(info.position);
        menu.setHeaderTitle(selectedAbility.getName());
        menu.add(0, CONTEXT_MENU_ABILITY_REMOVE, 0,
                R.string.race_administration_ability_list_context_menu_remove_ability);
    }

    /**
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onContextItemSelected(final MenuItem menuItem) {

        switch (menuItem.getItemId()) {

        case CONTEXT_MENU_ABILITY_REMOVE:
            return removeAbility();

        default:
            return super.onContextItemSelected(menuItem);
        }
    }

    private boolean removeAbility() {
        Logger.debug("selectedAbility: " + selectedAbility);
        adapter.remove(selectedAbility);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_admin_create:
            searchAbility();
            break;

        default:
            return super.onMenuItemSelected(featureId, item);
        }
        return true;
    }

    private void searchAbility() {
        Logger.debug("searchAbility");
        final Intent intent = new Intent(this, AbilitySearchActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SEARCH_ABILITY);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {

        // see which child activity is calling us back.
        switch (requestCode) {
        case REQUEST_CODE_SEARCH_ABILITY:
            if (resultCode != RESULT_CANCELED) {
                final Bundle extras = resultIntent.getExtras();
                final int abilityId = extras.getInt(INTENT_EXTRA_DATA_OBJECT);
                final Ability ability = gameSystem.getAbilityService().getAbilityById(abilityId,
                        gameSystem.getAllAbilities());
                adapter.add(ability);
            }
            break;

        default:
            throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }
    }

}
