package com.android.ash.charactersheet.gui.sheet;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.android.ash.charactersheet.FBAnalytics;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Displays all weapon attacks of a character. New weapon attacks can be created. Existing weapon attacks can be edited
 * or deleted.
 */
public class WeaponAttackPageFragment extends PageFragment {

    private static final int CONTEXT_MENU_DELETE_WEAPON_ATTACK = 100;

    private WeaponAttackListAdapter weaponAttackListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.page_attack;
    }

    @Override
    protected void doCreateView() {
        setFavoriteActionButton();

        final DieRollView dieRollView = getDieRollView();
        dieRollView.setOnClickListener(new HideOnClickListener());

        final ListView listView = getListView();
        listView.setTextFilterEnabled(true);
        listView.setOnCreateContextMenuListener(this);
    }

    private void setFavoriteActionButton() {
        final FloatingActionButton favoriteActionButton = view.findViewById(R.id.favorite_action_button);
        favoriteActionButton.setOnClickListener(view -> startActivity(new Intent(getActivity(), WeaponAttackSearchActivity.class)));
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
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, FBAnalytics.ScreenName.WEAPON_ATTACK);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "WeaponAttackPageFragment");
        firebaseAnalytics.getValue().logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);


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
    public void onCreateContextMenu(@NonNull final ContextMenu menu, @NonNull final View view, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final WeaponAttack weaponAttack = (WeaponAttack) getListView().getAdapter().getItem(info.position);
        menu.setHeaderTitle(weaponAttack.getName());
        menu.add(0, CONTEXT_MENU_DELETE_WEAPON_ATTACK, 0, R.string.weaponattack_list_delete);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        if (item.getItemId() == CONTEXT_MENU_DELETE_WEAPON_ATTACK) {
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
