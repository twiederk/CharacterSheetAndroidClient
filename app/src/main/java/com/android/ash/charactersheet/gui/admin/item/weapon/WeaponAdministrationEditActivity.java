package com.android.ash.charactersheet.gui.admin.item.weapon;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Weapon;

/**
 * Derived from the WeaponAdministrationActivity it allows to update a weapon.
 */
public class WeaponAdministrationEditActivity extends WeaponAdministrationActivity {

    @Override
    protected Weapon createForm() {
        final int weaponId = getIntent().getExtras().getInt(INTENT_EXTRA_DATA_OBJECT);
        final Weapon weapon = (Weapon) itemService.getItemById(weaponId, gameSystem.getAllWeapons());
        return weapon;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.weapon_administration_edit_title);
    }

    @Override
    protected void saveForm() {
        itemService.updateWeapon((Weapon) form);
        gameSystem.clear();
    }

}
