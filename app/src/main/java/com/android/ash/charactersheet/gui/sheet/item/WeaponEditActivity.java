package com.android.ash.charactersheet.gui.sheet.item;

import java.util.ArrayList;
import java.util.List;

import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;

/**
 * Activity to edit number of weapons in equipment of character.
 */
public class WeaponEditActivity extends ItemEditActivity {

    @Override
    protected List<Item> getAllExpandListViews() {

        return new ArrayList<Item>(itemService.getEquipableWeapons(gameSystem.getAllWeapons()));
    }

    @Override
    protected List<ItemGroup> getCharacterItems() {
        return character.getEquipment().getWeapons();
    }

    @Override
    protected void onSave(final List<ItemGroup> items) {
        characterService.updateWeapons(character, items);
    }

}
