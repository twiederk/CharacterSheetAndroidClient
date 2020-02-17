package com.android.ash.charactersheet.gui.admin.item.armor;

import android.os.Bundle;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.item.ItemAdministrationActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.numberview.PositiveNumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndNegativeNumberViewController;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.ArmorType;

import java.util.Arrays;

/**
 * Creates the weapon administration GUI, which allows to create or edit a weapon. The GUI allows to enter name, type,
 * cost weight, magical, damage, critical and description of the weapon. At the bottom a ok and cancel button allows to
 * save or cancel the action. Filling the GUI and reading the data from the GUI is handled by this class, while
 * providing the data and storing it must be handled by its derived classes.
 */
public abstract class ArmorAdministrationActivity extends ItemAdministrationActivity<Armor> {

    private Armor armor;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        armor = (Armor) form;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.armor_administration;
    }

    @Override
    protected void fillViews() {
        super.fillViews();
        setNumberViewController(new PositiveNumberViewController(armor.getArmorBonus()),
                R.id.armor_administration_bonus);
        setNumberViewController(new ZeroAndNegativeNumberViewController(armor.getArmorCheckPenalty()),
                R.id.armor_administration_penalty);
    }

    @Override
    protected SpinnerAdapter getTypeAdapter() {
        return new ArmorTypeArrayAdapter(this, displayService, Arrays.asList(ArmorType.values()));
    }

    @Override
    protected int getTypeSelectedId() {
        return armor.getArmorType().ordinal();
    }

    @Override
    protected void fillForm() {
        super.fillForm();
        armor.setArmorType((ArmorType) getSelectedItemOfSpinner(R.id.item_administration_type));
        armor.setArmorBonus(getIntegerOfNumberView(R.id.armor_administration_bonus));
        armor.setArmorCheckPenalty(getIntegerOfNumberView(R.id.armor_administration_penalty));
        Logger.debug("armor: " + form);
    }

}
