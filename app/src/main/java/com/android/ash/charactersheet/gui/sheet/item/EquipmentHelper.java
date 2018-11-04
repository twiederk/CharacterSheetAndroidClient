package com.android.ash.charactersheet.gui.sheet.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.util.ItemGroupComparator;

/**
 * Helper to display equipment of the character.
 */
public class EquipmentHelper {

    private final Character character;

    /**
     * Helper to display equipment of the character.
     * 
     * @param character
     *            The character to display its equipment.
     */
    public EquipmentHelper(final Character character) {
        this.character = character;
    }

    /**
     * Returns item group of the weapons of the character, ordered alphabetically.
     * 
     * @return List of character weapons wrapped in item groups.
     */
    public List<ExpandableListItem> getWeaponViews() {
        final List<ItemGroup> weapons = character.getEquipment().getWeapons();
        Collections.sort(weapons, new ItemGroupComparator());
        final List<ExpandableListItem> weaponViews = createExpandListViews(weapons);
        return weaponViews;
    }

    /**
     * Returns item group of the armor of the character, ordered alphabetically.
     * 
     * @return List of character armor wrapped in item groups.
     */
    public List<ExpandableListItem> getArmorViews() {
        final List<ItemGroup> armor = character.getEquipment().getArmor();
        Collections.sort(armor, new ItemGroupComparator());
        final List<ExpandableListItem> armorViews = createExpandListViews(armor);
        return armorViews;
    }

    /**
     * Returns item group of the goods of the character, ordered alphabetically.
     * 
     * @return List of character goods wrapped in item groups.
     */
    public List<ExpandableListItem> getGoodViews() {
        final List<ItemGroup> goods = character.getEquipment().getGoods();
        Collections.sort(goods, new ItemGroupComparator());
        final List<ExpandableListItem> goodViews = createExpandListViews(goods);
        return goodViews;
    }

    private List<ExpandableListItem> createExpandListViews(final List<? extends Object> objects) {
        final List<ExpandableListItem> expandListViews = new ArrayList<ExpandableListItem>();
        for (final Object object : objects) {
            final ExpandableListItem expandListView = new ExpandableListItem(object);
            expandListViews.add(expandListView);
        }
        return expandListViews;
    }

    /**
     * Returns list of item groups of all items. The number of items owned by the character is contained in the item
     * groups.
     * 
     * @param allItems
     *            List of all items.
     * @param characterItems
     *            List of character items.
     * @return List of item groups of all items.
     */
    public List<ExpandableListItem> createItemGroups(final List<Item> allItems, final List<ItemGroup> characterItems) {
        final List<ItemGroup> itemGroups = new ArrayList<ItemGroup>();
        for (final Item item : allItems) {
            final ItemGroup itemGroup = new ItemGroup();
            itemGroup.setItem(item);
            itemGroup.setNumber(0);
            final ItemGroup characterItemGroup = getCharacterItemGroup(item, characterItems);
            if (characterItemGroup != null) {
                itemGroup.setId(characterItemGroup.getId());
                itemGroup.setNumber(characterItemGroup.getNumber());
            }
            itemGroups.add(itemGroup);
        }
        Collections.sort(itemGroups, new ItemGroupComparator());
        final List<ExpandableListItem> expandListViews = new ArrayList<ExpandableListItem>();
        for (final ItemGroup itemGroup : itemGroups) {
            final ExpandableListItem expandListView = new ExpandableListItem(itemGroup);
            expandListViews.add(expandListView);
        }

        return expandListViews;
    }

    private ItemGroup getCharacterItemGroup(final Item item, final List<ItemGroup> characterItems) {
        for (final ItemGroup itemGroup : characterItems) {
            if (itemGroup.getItem().equals(item)) {
                return itemGroup;
            }
        }
        return null;
    }

}
