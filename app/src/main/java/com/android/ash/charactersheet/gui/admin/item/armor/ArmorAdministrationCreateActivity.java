package com.android.ash.charactersheet.gui.admin.item.armor;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.ArmorType;
import com.d20charactersheet.framework.boc.model.QualityType;

/**
 * Derived from the WeaponAdministrationActivity it allows to create a new weapon.
 */
public class ArmorAdministrationCreateActivity extends ArmorAdministrationActivity {

    @Override
    protected Armor createForm() {
        final Armor armor = new Armor();
        armor.setName("");
        armor.setArmorType(ArmorType.LIGHT);
        armor.setWeight(1);
        armor.setCost(0);
        armor.setQualityType(QualityType.NORMAL);
        armor.setArmorBonus(1);
        armor.setArmorCheckPenalty(0);
        armor.setDescription("");
        return armor;
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.armor_administration_create_title);
    }

    @Override
    protected void saveForm() {
        if (!form.getName().trim().isEmpty()) {
            itemService.createArmor((Armor) form);
            gameSystem.clear();
        }
    }
}
