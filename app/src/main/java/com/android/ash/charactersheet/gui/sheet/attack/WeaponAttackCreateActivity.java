package com.android.ash.charactersheet.gui.sheet.attack;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import java.util.ArrayList;
import java.util.EnumSet;

import android.content.Intent;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.AttackWield;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponAttack;

/**
 * Displays a weapon attack and creates it.
 */
public class WeaponAttackCreateActivity extends WeaponAttackActivity {

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.weaponattack_create_title);
    }

    @Override
    protected WeaponAttack createForm() {
        final Weapon weapon = getWeaponFromIntent();
        final WeaponAttack weaponAttack = new WeaponAttack();
        weaponAttack.setWeapon(weapon);
        weaponAttack.setName(weapon.getName());
        weaponAttack.setDescription("");
        weaponAttack.setAttackWield(getAttackWield(weapon));
        weaponAttack.setAmmunition(0);
        return weaponAttack;
    }

    private Weapon getWeaponFromIntent() {
        final Intent intent = getIntent();
        final int weaponId = intent.getExtras().getInt(INTENT_EXTRA_DATA_OBJECT);
        final Weapon weapon = getWeaponById(weaponId);
        return weapon;
    }

    private Weapon getWeaponById(final int weaponId) {
        for (final Weapon weapon : gameSystem.getAllWeapons()) {
            if (weapon.getId() == weaponId) {
                return weapon;
            }
        }
        throw new IllegalArgumentException("Can't find weapon with id: " + weaponId);
    }

    private AttackWield getAttackWield(final Weapon weapon) {
        final EnumSet<AttackWield> attackWields = gameSystem.getRuleService().getAttackWields(weapon);
        return new ArrayList<AttackWield>(attackWields).get(0);
    }

    @Override
    protected void saveForm() {
        gameSystem.getCharacterService().createWeaponAttack(character, form);
        gameSystem.getRuleService().calculateWeaponAttack(character, form);
    }

}
