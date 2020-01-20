package com.android.ash.charactersheet.gui.sheet.attack;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.WeaponAttack;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

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
        return getWeaponAttackFromIntent();
    }

    private WeaponAttack getWeaponAttackFromIntent() {
        final int weaponAttackId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        return getWeaponAttackById(weaponAttackId);
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
