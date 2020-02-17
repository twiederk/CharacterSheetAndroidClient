package com.android.ash.charactersheet.gui.admin.item.weapon;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.item.ItemAdministrationActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.SpinnerArrayAdapter;
import com.d20charactersheet.framework.boc.model.CombatType;
import com.d20charactersheet.framework.boc.model.Critical;
import com.d20charactersheet.framework.boc.model.Damage;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponCategory;
import com.d20charactersheet.framework.boc.model.WeaponEncumbrance;
import com.d20charactersheet.framework.boc.model.WeaponFamily;
import com.d20charactersheet.framework.boc.model.WeaponType;
import com.d20charactersheet.framework.boc.util.WeaponFamilyComparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Creates the weapon administration GUI, which allows to create or edit a weapon. The GUI allows to enter name, type,
 * cost weight, magical, damage, critical and description of the weapon. At the bottom a ok and cancel button allows to
 * save or cancel the action. Filling the GUI and reading the data from the GUI is handled by this class, while
 * providing the data and storing it must be handled by its derived classes.
 */
public abstract class WeaponAdministrationActivity extends ItemAdministrationActivity<Weapon> {

    private Weapon weapon;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weapon = (Weapon) form;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.weapon_administration;
    }

    @Override
    protected void fillViews() {
        super.fillViews();
        setEnhancementBonus();
        setDamage();
        setCritical();
        setCombatType();
        setWeaponEncumbrance();
        setWeaponCategory();
        setWeaponFamily();
        setRangeIncrement();
    }

    @Override
    protected SpinnerAdapter getTypeAdapter() {
        return new WeaponTypeArrayAdapter(this, displayService, Arrays.asList(WeaponType.values()));
    }

    @Override
    protected int getTypeSelectedId() {
        return weapon.getWeaponType().ordinal();
    }

    private void setEnhancementBonus() {
        setText(Integer.toString(weapon.getEnhancementBonus()), R.id.weapon_administration_enhancement_bonus);
    }

    private void setDamage() {
        final Damage damage = weapon.getDamage();
        setText(Integer.toString(damage.getNumberOfDice()), R.id.weapon_administration_damage_number_of_dice);

        final ArrayAdapter<Die> dieArrayAdapter = new DieArrayAdapter(this, displayService, Arrays.asList(Die.values()));
        final int dieOrdinal = weapon.getDamage().getDie().ordinal();
        setSpinner(R.id.weapon_administration_damage_die, dieArrayAdapter, dieOrdinal);
    }

    private void setCritical() {
        final Critical critical = weapon.getCritical();
        setText(Integer.toString(critical.getHit()), R.id.weapon_administration_critical_hit);
        setText(Integer.toString(critical.getMultiplier()), R.id.weapon_administration_critical_multiplier);
    }

    private void setCombatType() {
        final ArrayAdapter<CombatType> combatTypeAdapter = new CombatTypeAdapter(this, displayService,
                Arrays.asList(CombatType.values()));
        final int ordinal = weapon.getCombatType().ordinal();
        setSpinner(R.id.weapon_administration_combat_type, combatTypeAdapter, ordinal);
    }

    private void setWeaponEncumbrance() {
        final ArrayAdapter<WeaponEncumbrance> weaponEncumbranceAdapter = new WeaponEncumbranceAdapter(this,
                displayService, Arrays.asList(WeaponEncumbrance.values()));
        final int ordinal = weapon.getWeaponEncumbrance().ordinal();
        setSpinner(R.id.weapon_administration_weapon_encumbrance, weaponEncumbranceAdapter, ordinal);
    }

    private void setWeaponCategory() {
        final ArrayAdapter<WeaponCategory> weaponCategoryAdapter = new WeaponCategoryAdapter(this, displayService,
                Arrays.asList(WeaponCategory.values()));
        final int ordinal = weapon.getWeaponCategory().ordinal();
        setSpinner(R.id.weapon_administration_weapon_category, weaponCategoryAdapter, ordinal);
    }

    private void setWeaponFamily() {
        final List<WeaponFamily> allWeaponFamilies = gameSystem.getAllWeaponFamilies();
        Collections.sort(allWeaponFamilies, new WeaponFamilyComparator());
        final ArrayAdapter<WeaponFamily> weaponFamilyAdapter = new SpinnerArrayAdapter<>(this,
                displayService, allWeaponFamilies);
        final int selectedIndex = getWeaponFamilyIndex(weapon.getWeaponFamily(), allWeaponFamilies);
        setSpinner(R.id.weapon_administration_weapon_family, weaponFamilyAdapter, selectedIndex);
    }

    private int getWeaponFamilyIndex(final WeaponFamily weaponFamily, final List<WeaponFamily> allWeaponFamilies) {
        for (int i = 0; i < allWeaponFamilies.size(); i++) {
            if (allWeaponFamilies.get(i).equals(weaponFamily)) {
                return i;
            }
        }
        return 0;
    }

    private void setRangeIncrement() {
        setText(Integer.toString(weapon.getRangeIncrement()), R.id.weapon_administration_range_increment);
    }

    @Override
    protected void fillForm() {
        super.fillForm();
        weapon.setWeaponType((WeaponType) getSelectedItemOfSpinner(R.id.item_administration_type));
        weapon.setEnhancementBonus(getIntegerOfTextView(R.id.weapon_administration_enhancement_bonus));
        fillDamage();
        fillCritical();
        weapon.setCombatType((CombatType) getSelectedItemOfSpinner(R.id.weapon_administration_combat_type));
        weapon.setWeaponEncumbrance((WeaponEncumbrance) getSelectedItemOfSpinner(R.id.weapon_administration_weapon_encumbrance));
        weapon.setWeaponCategory((WeaponCategory) getSelectedItemOfSpinner(R.id.weapon_administration_weapon_category));
        weapon.setWeaponFamily((WeaponFamily) getSelectedItemOfSpinner(R.id.weapon_administration_weapon_family));
        weapon.setRangeIncrement(getIntegerOfTextView(R.id.weapon_administration_range_increment));
        Logger.debug("weapon: " + form);
    }

    private void fillDamage() {
        final Damage damage = weapon.getDamage();
        damage.setNumberOfDice(getIntegerOfTextView(R.id.weapon_administration_damage_number_of_dice));
        damage.setDie((Die) getSelectedItemOfSpinner(R.id.weapon_administration_damage_die));
    }

    private void fillCritical() {
        final Critical critical = weapon.getCritical();
        critical.setHit(getIntegerOfTextView(R.id.weapon_administration_critical_hit));
        critical.setMultiplier(getIntegerOfTextView(R.id.weapon_administration_critical_multiplier));
    }

}
