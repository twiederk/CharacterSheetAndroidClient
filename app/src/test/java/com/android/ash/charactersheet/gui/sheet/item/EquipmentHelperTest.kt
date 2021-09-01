package com.android.ash.charactersheet.gui.sheet.item

import com.android.ash.charactersheet.gui.util.ExpandableListItem
import com.d20charactersheet.framework.boc.model.Good
import com.d20charactersheet.framework.boc.model.Item
import com.d20charactersheet.framework.boc.model.ItemGroup
import com.d20charactersheet.framework.boc.service.ItemService
import com.d20charactersheet.framework.dsl.createCharacter
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class EquipmentHelperTest {

    @Test
    fun testGetAllGoods() {
        // Arrange
        val character = createCharacter {
            equipment {
                this.goods = createGoodsAndFillBackpack()
            }
        }

        val equipmentHelper = EquipmentHelper(character)
        val itemService = Mockito.mock(ItemService::class.java)
        Mockito.`when`(itemService.allGoods).thenReturn(
            listOf(
                createGood(0, "Backpack"),  //
                createGood(68, "Waterskin"),  //
                createGood(51, "Trail Rations (per Day)"),  //
                createGood(3, "Bedroll"),  //
                createGood(22, "Flint and Steel"),  //
                createGood(10, "Candle"),  //
                createGood(65, "Torch") //
            )
        )

        // Act
        val allGoods = equipmentHelper.createItemGroups(
            ArrayList<Item>(itemService.allGoods),
            character.equipment.goods
        )

        // Assert
        assertThat(allGoods).hasSize(7)
        assertItem(allGoods, "Backpack", 1)
        assertItem(allGoods, "Waterskin", 1)
        assertItem(allGoods, "Trail Rations (per Day)", 10)
        assertItem(allGoods, "Bedroll", 1)
        assertItem(allGoods, "Flint and Steel", 1)
        assertItem(allGoods, "Candle", 8)
        assertItem(allGoods, "Torch", 0)
    }

    private fun createGoodsAndFillBackpack(): MutableList<ItemGroup> {
        val backpack = createGood(0, "Backpack")
        val waterSkin = createGood(68, "Waterskin")
        val trailRation = createGood(51, "Trail Rations (per Day)")
        val bedroll = createGood(3, "Bedroll")
        val flintAndSteel = createGood(22, "Flint and Steel")
        val candle = createGood(10, "Candle")
        val goods = mutableListOf<ItemGroup>()
        goods.add(createItemGroup(backpack, 1))
        goods.add(createItemGroup(waterSkin, 1))
        goods.add(createItemGroup(trailRation, 10))
        goods.add(createItemGroup(bedroll, 1))
        goods.add(createItemGroup(flintAndSteel, 1))
        goods.add(createItemGroup(candle, 8))
        return goods
    }

    private fun createItemGroup(good: Good, number: Int): ItemGroup =
        ItemGroup().apply { item = good; this.number = number }

    private fun createGood(id: Int, name: String): Good =
        Good().apply { this.id = id; this.name = name }

    private fun assertItem(expandListViews: List<ExpandableListItem>, name: String, number: Int) {
        for (expandListView in expandListViews) {
            val itemGroup = expandListView.getObject() as ItemGroup
            val item = itemGroup.item
            if (item.name == name) {
                assertThat(itemGroup.number).isEqualTo(number)
                return
            }
        }
        fail<Any>("Missing item: $name")
    }

}