package com.android.ash.charactersheet.gui.sheet;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.sheet.attack.CriticalOnClickListener;
import com.android.ash.charactersheet.gui.sheet.attack.DamageOnClickListener;
import com.android.ash.charactersheet.gui.sheet.attack.WeaponAttackListAdapter;
import com.android.ash.charactersheet.gui.sheet.attack.WeaponAttackSearchActivity;
import com.android.ash.charactersheet.gui.util.HideOnClickListener;
import com.android.ash.charactersheet.gui.widget.attackbonusview.AttackOnClickListener;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.WeaponAttack;
import com.d20charactersheet.framework.boc.util.WeaponAttackComparator;

/**
 * Displays all weapon attacks of a character. New weapon attacks can be created. Existing weapon attacks can be edited
 * or deleted.
 */
public class WeaponAttackPageFragment extends PageFragment {

    private static final int CONTEXT_MENU_DELETE_WEAPONATTACK = 100;

    private WeaponAttackListAdapter weaponAttackListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.page_attack;
    }

    @Override
    protected void doCreateView() {
        final DieRollView dieRollView = getDieRollView();
        dieRollView.setOnClickListener(new HideOnClickListener());

        final ListView listView = getListView();
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
    }

    private DieRollView getDieRollView() {
        return view.findViewById(R.id.attack_list_die_roll);
    }

    private ListView getListView() {
        return view.findViewById(R.id.attack_list);
    }

    @Override
    public void onResume() {
        super.onResume();

        for (final WeaponAttack weaponAttack : character.getWeaponAttacks()) {
            ruleService.calculateWeaponAttack(character, weaponAttack);
        }

        final DieRollView dieRollView = getDieRollView();
        weaponAttackListAdapter = new WeaponAttackListAdapter(getActivity(), displayService,
                R.layout.listitem_weaponattack, character.getWeaponAttacks());
        weaponAttackListAdapter.setAttackOnClickListener(new AttackOnClickListener(ruleService, dieRollView));
        weaponAttackListAdapter.setDamageOnClickListener(new DamageOnClickListener(ruleService, dieRollView));
        weaponAttackListAdapter.setCriticalOnClickListener(new CriticalOnClickListener(ruleService, dieRollView));
        weaponAttackListAdapter.sort(new WeaponAttackComparator());

        getListView().setAdapter(weaponAttackListAdapter);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_page_weapon_attack, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.menu_page_weapon_attack_create) {
            createWeaponAttack();
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void createWeaponAttack() {
        final Intent intent = new Intent(getActivity(), WeaponAttackSearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View v, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final WeaponAttack weaponAttack = (WeaponAttack) getListView().getAdapter().getItem(info.position);
        menu.setHeaderTitle(weaponAttack.getName());
        menu.add(0, CONTEXT_MENU_DELETE_WEAPONATTACK, 0, R.string.weaponattack_list_delete);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        if (item.getItemId() == CONTEXT_MENU_DELETE_WEAPONATTACK) {
            deleteWeaponAttack(item);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void deleteWeaponAttack(final MenuItem item) {
        final AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
        final WeaponAttack weaponAttack = (WeaponAttack) getListView().getAdapter().getItem(menuInfo.position);
        characterService.deleteWeaponAttack(character, weaponAttack);
        weaponAttackListAdapter.remove(weaponAttack);
    }

}
