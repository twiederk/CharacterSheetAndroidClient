package com.android.ash.charactersheet.gui.sheet.attack;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.ash.charactersheet.Constants;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.BaseCharacterSheetActivity;
import com.android.ash.charactersheet.gui.util.SearchTextWatcher;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponCategory;
import com.d20charactersheet.framework.boc.util.ItemComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays an alphabetical list of all weapons and a search box at the top of it. Entering letters in the search box
 * filters the list box. Selecting a weapon leads to the weapon create activity.
 */
public class WeaponAttackSearchActivity extends BaseCharacterSheetActivity implements OnItemClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.weaponattack_search;
    }

    @Override
    protected void doCreate() {
        final List<Weapon> weapons = getWeapons();
        final ArrayAdapter<Weapon> adapter = new WeaponAttackSearchAdapter(this, displayService, weapons);
        adapter.sort(new ItemComparator());

        final ListView weaponListView = getListView();
        weaponListView.setAdapter(adapter);
        weaponListView.setTextFilterEnabled(true);
        weaponListView.setOnItemClickListener(this);

        final EditText searchEditText = findViewById(R.id.weaponattack_search_text);
        searchEditText.addTextChangedListener(new SearchTextWatcher(weaponListView));

    }

    private List<Weapon> getWeapons() {
        final List<Weapon> allWeapons = gameSystem.getAllWeapons();
        final List<Weapon> weapons = new ArrayList<>(allWeapons.size());
        for (final Weapon weapon : allWeapons) {
            if (!WeaponCategory.AMMUNITION.equals(weapon.getWeaponCategory())) {
                weapons.add(weapon);
            }
        }
        return weapons;
    }

    private ListView getListView() {
        return findViewById(R.id.weaponattack_search_list);
    }

    @Override
    public void onItemClick(final AdapterView<?> listView, final View view, final int position, final long id) {
        final Weapon weapon = (Weapon) listView.getItemAtPosition(position);
        final Intent intent = new Intent(this, WeaponAttackCreateActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_DATA_OBJECT, weapon.getId());
        startActivity(intent);
        finish();
    }

}
