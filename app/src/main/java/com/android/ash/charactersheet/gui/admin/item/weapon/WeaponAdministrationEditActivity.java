package com.android.ash.charactersheet.gui.admin.item.weapon;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Weapon;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Derived from the WeaponAdministrationActivity it allows to update a weapon.
 */
public class WeaponAdministrationEditActivity extends WeaponAdministrationActivity {

    @Override
    protected Weapon createForm() {
        final int weaponId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        return (Weapon) itemService.getItemById(weaponId, gameSystem.getAllWeapons());
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
