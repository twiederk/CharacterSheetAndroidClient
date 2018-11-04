package com.android.ash.charactersheet.gui.sheet.attack;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.WeaponAttack;

/**
 * Displays a weapon attack and updates it.
 */
public class WeaponAttackEditActivity extends WeaponAttackActivity {

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.weaponattack_edit_title);
    }

    @Override
    protected WeaponAttack createForm() {
        final WeaponAttack weaponAttack = getWeaponAttackFromIntent();
        return weaponAttack;
    }

    private WeaponAttack getWeaponAttackFromIntent() {
        final int weaponAttackId = getIntent().getExtras().getInt(INTENT_EXTRA_DATA_OBJECT);
        final WeaponAttack weaponAttack = getWeaponAttackById(weaponAttackId);
        return weaponAttack;
    }

    private WeaponAttack getWeaponAttackById(final int weaponAttackId) {
        for (final WeaponAttack weaponAttack : character.getWeaponAttacks()) {
            if (weaponAttack.getId() == weaponAttackId) {
                return weaponAttack;
            }
        }
        throw new IllegalArgumentException("Can't get weapon attack with id: " + weaponAttackId);
    }

    @Override
    protected void saveForm() {
        gameSystem.getCharacterService().updateWeaponAttack(form);
        gameSystem.getRuleService().calculateWeaponAttack(character, form);
    }

}
