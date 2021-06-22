package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@KoinApiExtension
class CharacterCreatorEquipmentTest {

    private val characterCreatorViewModel: CharacterCreatorViewModel = mock()

    private val weapon1 = Weapon().apply { id = 1 }

    private val armor1 = Armor().apply { id = 1 }

    private val good1 = Good().apply { id = 1 }
    private val good2 = Good().apply { id = 2 }
    private val good3 = Good().apply { id = 3 }

    @Before
    fun before() {
        val starterPackBoxViewModels = listOf(
            StarterPackBoxViewModel(
                StarterPackBox().also { starterPackBox ->
                    starterPackBox.add(
                        StarterPackBoxItemOption().also {
                            it.add(ItemGroup().apply { item = weapon1; number = 1 })
                        })
                }),
            StarterPackBoxViewModel(
                StarterPackBox().also { starterPackBox ->
                    starterPackBox.add(
                        StarterPackBoxItemOption().also {
                            it.add(ItemGroup().apply { item = armor1; number = 1 })
                        })
                }),
            StarterPackBoxViewModel(
                StarterPackBox().also { starterPackBox ->
                    starterPackBox.add(
                        StarterPackBoxItemOption().also {
                            it.add(ItemGroup().apply { item = good1; number = 1 })
                        })
                }),
            StarterPackBoxViewModel(
                StarterPackBox().also { starterPackBox ->
                    starterPackBox.add(
                        StarterPackBoxItemOption().also {
                            it.add(ItemGroup().apply { id = 1; item = good2; number = 1 })
                            it.add(ItemGroup().apply { id = 2; item = good3; number = 1 })
                        })
                }),
        )
        whenever(characterCreatorViewModel.starterPackBoxViewModels).thenReturn(
            starterPackBoxViewModels
        )
    }

    @Test
    fun getWeapons_oneItemGroupWithWeapon() {

        // act
        val weapons: List<ItemGroup> =
            CharacterCreatorEquipment().getWeapons(characterCreatorViewModel)

        // assert
        assertThat(weapons).hasSize(1)
        assertThat(weapons[0].id).isEqualTo(-1)
        assertThat(weapons[0].item).isEqualTo(weapon1)
    }

    @Test
    fun getArmor_oneItemGroupWithArmor() {

        // act
        val armor: List<ItemGroup> =
            CharacterCreatorEquipment().getArmor(characterCreatorViewModel)

        // assert
        assertThat(armor).hasSize(1)
        assertThat(armor[0].id).isEqualTo(-1)
        assertThat(armor[0].item).isEqualTo(armor1)
    }

    @Test
    fun getGoods_threeItemGroupWithGoods() {

        // act
        val goods: List<ItemGroup> =
            CharacterCreatorEquipment().getGoods(characterCreatorViewModel)

        // assert
        assertThat(goods).hasSize(3)
        assertThat(goods).extracting("id").containsOnly(-1, -1, -1)
        assertThat(goods.map { it.item }).containsExactlyInAnyOrder(good1, good2, good3)
    }

    @Test
    fun fillEquipment_twoItemsOfEachCategory() {
        // arrange
        val character = Character()

        // act
        CharacterCreatorEquipment().fillEquipment(character, characterCreatorViewModel)

        // assert
        assertThat(character.equipment.weapons).extracting("id").containsOnly(-1)
        assertThat(character.equipment.weapons.map { it.item }).containsExactlyInAnyOrder(
            weapon1
        )
        assertThat(character.equipment.armor).extracting("id").containsOnly(-1)
        assertThat(character.equipment.armor.map { it.item }).containsExactlyInAnyOrder(
            armor1
        )
        assertThat(character.equipment.goods).extracting("id").containsOnly(-1, -1, -1)
        assertThat(character.equipment.goods.map { it.item }).containsExactlyInAnyOrder(
            good1,
            good2,
            good3
        )
    }

}