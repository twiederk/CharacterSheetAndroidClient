package com.android.ash.charactersheet.gui.sheet.item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.service.ItemService;
import com.d20charactersheet.framework.boc.service.ItemServiceImpl;
import com.d20charactersheet.framework.dac.dao.dummy.DummyItemDao;

public class EquipmentHelperTest extends TestCase {

    private List<ItemGroup> createGoodsAndFillBackpack() {
        final Good backpack = createGood(0, "Backpack");
        final Good waterskin = createGood(68, "Waterskin");
        final Good trailRation = createGood(51, "Trail Rations (per Day)");
        final Good bedroll = createGood(3, "Bedroll");
        final Good flintAndSteel = createGood(22, "Flint and Steel");
        final Good candle = createGood(10, "Candle");

        final List<ItemGroup> goods = new LinkedList<ItemGroup>();
        goods.add(creatItemGroup(backpack, 1));
        goods.add(creatItemGroup(waterskin, 1));
        goods.add(creatItemGroup(trailRation, 10));
        goods.add(creatItemGroup(bedroll, 1));
        goods.add(creatItemGroup(flintAndSteel, 1));
        goods.add(creatItemGroup(candle, 8));
        return goods;
    }

    private ItemGroup creatItemGroup(final Good good, final int number) {
        final ItemGroup goodGroup = new ItemGroup();
        goodGroup.setItem(good);
        goodGroup.setNumber(number);
        return goodGroup;
    }

    private Good createGood(final int id, final String name) {
        final Good good = new Good();
        good.setId(id);
        good.setName(name);
        return good;
    }

    public void testGetAllGoods() throws Exception {
        final List<ItemGroup> goods = createGoodsAndFillBackpack();
        final Character character = new Character();
        character.getEquipment().setGoods(goods);
        final EquipmentHelper equipmentHelper = new EquipmentHelper(character);
        final ItemService itemService = new ItemServiceImpl(new DummyItemDao(WEAPON_FAMILY, WEAPON, ARMOR, GOOD));

        final List<ExpandableListItem> allGoods = equipmentHelper.createItemGroups(
                new ArrayList<Item>(itemService.getAllGoods()), character.getEquipment().getGoods());
        assertNotNull(allGoods);
        assertEquals(131, allGoods.size());
        assertItem(allGoods, "Backpack", 1);
        assertItem(allGoods, "Waterskin", 1);
        assertItem(allGoods, "Trail Rations (per Day)", 10);
        assertItem(allGoods, "Bedroll", 1);
        assertItem(allGoods, "Flint and Steel", 1);
        assertItem(allGoods, "Candle", 8);
        assertItem(allGoods, "Torch", 0);
    }

    private void assertItem(final List<ExpandableListItem> expandListViews, final String name, final int number) {
        for (final ExpandableListItem expandListView : expandListViews) {
            final ItemGroup itemGroup = (ItemGroup) expandListView.getObject();
            final Item item = itemGroup.getItem();
            if (item.getName().equals(name)) {
                assertEquals(number, itemGroup.getNumber());
                return;
            }
        }
        fail("Missing item: " + name);
    }

}
