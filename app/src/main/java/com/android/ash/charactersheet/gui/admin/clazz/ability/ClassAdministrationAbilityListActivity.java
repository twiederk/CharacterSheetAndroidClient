package com.android.ash.charactersheet.gui.admin.clazz.ability;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.util.AbilitySearchActivity;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.ClassAbility;
import com.d20charactersheet.framework.boc.service.AbilityService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.util.ClassAbilityComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Displays all skills with a checkbox to select them as class skills.
 */
public class ClassAdministrationAbilityListActivity extends LogActivity implements OnItemClickListener {

    private static final int CONTEXT_MENU_CLASS_ABILITY_REMOVE = 1;

    private static final int REQUEST_CODE_SEARCH_ABILITY = 100;

    private static final int REQUEST_CODE_EDIT_CLASS_ABILITY = 200;

    private GameSystem gameSystem;
    private List<ClassAbility> classAbilities;
    private ArrayAdapter<ClassAbility> adapter;
    private ClassAbility selectedClassAbility;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_administration_abilities);
        setTitle(R.string.class_administration_ability_list_title);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();

        setAbilities();
    }

    private void setAbilities() {
        classAbilities = getClassAbilitiesFromIntent();

        adapter = new NameDisplayArrayAdapter<>(this, gameSystem.getDisplayService(), classAbilities);
        final ListView classAbilityListView = getListView();
        classAbilityListView.setOnCreateContextMenuListener(this);
        classAbilityListView.setOnItemClickListener(this);
        classAbilityListView.setAdapter(adapter);
    }

    private List<ClassAbility> getClassAbilitiesFromIntent() {
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        final int[] classAbilitiesData = Objects.requireNonNull(extras).getIntArray(INTENT_EXTRA_DATA_OBJECT);
        final List<ClassAbility> classAbilitiesFromIntent = new ArrayList<>(Objects.requireNonNull(classAbilitiesData).length / 2);
        final AbilityService abilityService = gameSystem.getAbilityService();
        final List<Ability> allAbilities = gameSystem.getAllAbilities();
        for (int i = 0; i < classAbilitiesData.length; i = i + 2) {
            final int abilityId = classAbilitiesData[i];
            final int level = classAbilitiesData[i + 1];
            final Ability ability = abilityService.getAbilityById(abilityId, allAbilities);
            final ClassAbility classAbility = new ClassAbility(ability);
            classAbility.setLevel(level);
            classAbilitiesFromIntent.add(classAbility);
        }

        return classAbilitiesFromIntent;
    }

    private ListView getListView() {
        return (ListView) findViewById(android.R.id.list);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            final Intent resultIntent = new Intent();
            final int[] classAbilitiesData = new int[classAbilities.size() * 2];
            for (int i = 0; i < classAbilities.size(); i++) {
                final ClassAbility classAbility = classAbilities.get(i);
                classAbilitiesData[i * 2] = classAbility.getAbility().getId();
                classAbilitiesData[i * 2 + 1] = classAbility.getLevel();
            }
            resultIntent.putExtra(INTENT_EXTRA_DATA_OBJECT, classAbilitiesData);
            setResult(RESULT_OK, resultIntent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View view, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        selectedClassAbility = (ClassAbility) getListView().getAdapter().getItem(info.position);
        menu.setHeaderTitle(selectedClassAbility.getAbility().getName());
        menu.add(0, CONTEXT_MENU_CLASS_ABILITY_REMOVE, 0,
                R.string.class_administration_ability_list_context_menu_remove_class_ability);
    }

    /**
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onContextItemSelected(final MenuItem menuItem) {

        if (menuItem.getItemId() == CONTEXT_MENU_CLASS_ABILITY_REMOVE) {
            return removeClassAbility();
        }
        return super.onContextItemSelected(menuItem);
    }

    @SuppressWarnings("SameReturnValue")
    private boolean removeClassAbility() {
        Logger.debug("selectedClassAbility: " + selectedClassAbility);
        adapter.remove(selectedClassAbility);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
        if (item.getItemId() == R.id.menu_admin_create) {
            searchAbility();
        } else {
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
                final int abilityId = Objects.requireNonNull(extras).getInt(INTENT_EXTRA_DATA_OBJECT);
                final Ability ability = gameSystem.getAbilityService().getAbilityById(abilityId,
                        gameSystem.getAllAbilities());
                final ClassAbility classAbility = new ClassAbility(ability);
                classAbility.setLevel(1);
                adapter.add(classAbility);
                editClassAbility(classAbility);
            }
            break;

        case REQUEST_CODE_EDIT_CLASS_ABILITY:
            if (resultCode != RESULT_CANCELED) {
                final Bundle extras = resultIntent.getExtras();
                final int[] classAbilityData = Objects.requireNonNull(extras).getIntArray(INTENT_EXTRA_DATA_OBJECT);
                final int abilityId = Objects.requireNonNull(classAbilityData)[0];
                final int level = classAbilityData[1];
                final Ability ability = gameSystem.getAbilityService().getAbilityById(abilityId,
                        gameSystem.getAllAbilities());
                final ClassAbility classAbility = new ClassAbility(ability);
                classAbility.setLevel(level);
                updateClassAbility(classAbility);
            }
            break;

        default:
            throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }
    }

    private void updateClassAbility(final ClassAbility classAbility) {
        for (final ClassAbility currentClassAbility : classAbilities) {
            if (currentClassAbility.getAbility().getId() == classAbility.getAbility().getId()) {
                currentClassAbility.setLevel(classAbility.getLevel());
            }
        }
        adapter.notifyDataSetChanged();
        adapter.sort(new ClassAbilityComparator());
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        final ClassAbility classAbility = (ClassAbility) adapterView.getItemAtPosition(position);
        editClassAbility(classAbility);
    }

    private void editClassAbility(final ClassAbility classAbility) {
        final Intent intent = new Intent(this, ClassAdministrationAbilityEditActivity.class);
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT,
                new int[] { classAbility.getAbility().getId(), classAbility.getLevel() });
        startActivityForResult(intent, REQUEST_CODE_EDIT_CLASS_ABILITY);
    }

}
