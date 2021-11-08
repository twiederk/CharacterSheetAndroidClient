package com.android.ash.charactersheet.gui.main.charactercreator.viewmodel

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.Armor
import com.d20charactersheet.framework.boc.model.EquipmentPack
import com.d20charactersheet.framework.boc.model.Good
import com.d20charactersheet.framework.boc.model.StarterPack
import com.d20charactersheet.framework.boc.model.StarterPackBox
import com.d20charactersheet.framework.boc.model.StarterPackBoxItemOption
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.service.CharacterClassService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.ItemService
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class EquipmentScreenViewModelKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }

    @Before
    fun before() {
        val gameSystem: GameSystem = mock()
        val characterClassService: CharacterClassService = mock()
        whenever(gameSystem.allWeapons).thenReturn(
            listOf(
                Weapon().apply { name = "myWeapon" },
                Weapon().apply { name = "anotherWeapon" })
        )
        whenever(gameSystem.allArmor).thenReturn(
            listOf(
                Armor().apply { name = "myArmor" },
                Armor().apply { name = "anotherArmor" })
        )
        whenever(gameSystem.allGoods).thenReturn(
            listOf(
                Good().apply { name = "myGood" },
                Good().apply { name = "anotherGood" })
        )
        whenever(gameSystem.allEquipmentPacks).thenReturn(
            listOf(
                EquipmentPack(
                    id = 1,
                    name = "myPack",
                    itemGroups = mutableListOf()
                ), EquipmentPack(
                    id = 2,
                    name = "anotherPack",
                    itemGroups = mutableListOf()
                )
            )
        )

        whenever(characterClassService.getStarterPack(any(), any())).thenReturn(
            StarterPack().also { starterPack ->
                starterPack.add(StarterPackBox().also { it.add(StarterPackBoxItemOption()) })
                starterPack.add(StarterPackBox().also { it.add(StarterPackBoxItemOption()) })
                starterPack.add(StarterPackBox().also { it.add(StarterPackBoxItemOption()) })
                starterPack.add(StarterPackBox().also { it.add(StarterPackBoxItemOption()) })
            }
        )

        val itemService: ItemService = mock()

        whenever(gameSystem.characterClassService).thenReturn(characterClassService)
        whenever(gameSystem.itemService).thenReturn(itemService)

        gameSystemHolder.gameSystem = gameSystem
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun constructor_initCharacter_characterWithDefaultValue() {

        // act
        val equipmentScreenViewModel = EquipmentScreenViewModel(gameSystemHolder)

        // assert
        assertThat(equipmentScreenViewModel.starterPackBoxViewModels).hasSize(4)
    }

    @Test
    fun reset_resetProperties_allPropertiesAreReset() {
        // arrange
        val underTest = EquipmentScreenViewModel(gameSystemHolder)
        underTest.starterPackBoxViewModels =
            listOf(StarterPackBoxViewModel(StarterPackBox().also { it.add(StarterPackBoxItemOption()) }))

        // act
        underTest.reset(1)

        // assert
        assertThat(underTest.starterPackBoxViewModels).hasSize(4)
    }

}