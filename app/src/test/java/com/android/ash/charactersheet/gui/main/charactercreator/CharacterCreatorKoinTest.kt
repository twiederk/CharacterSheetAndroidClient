package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AbilityScoresScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AppearanceScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.ClassScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.EquipmentScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.RaceScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.StarterPackBoxViewModel
import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.Armor
import com.d20charactersheet.framework.boc.model.AttackWield
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.EquipmentPack
import com.d20charactersheet.framework.boc.model.Good
import com.d20charactersheet.framework.boc.model.Item
import com.d20charactersheet.framework.boc.model.ItemGroup
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Sex
import com.d20charactersheet.framework.boc.model.StarterPackBox
import com.d20charactersheet.framework.boc.model.StarterPackBoxItemOption
import com.d20charactersheet.framework.boc.model.StarterPackBoxPackOption
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.model.WeaponAttack
import com.d20charactersheet.framework.boc.model.WeaponEncumbrance
import com.d20charactersheet.framework.boc.model.XpTable
import com.d20charactersheet.framework.boc.service.CharacterClassService
import com.d20charactersheet.framework.boc.service.CharacterService
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.RaceService
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CharacterCreatorKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val characterHolder: CharacterHolder by inject()

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
        declareMock<CharacterHolder>()
        declareMock<FirebaseAnalytics>()
    }

    @Test
    fun createCharacter_everythingIsFine_createNewCharacter() {
        // arrange
        val characterService: CharacterService = mock()
        val gameSystem: GameSystem = mock()

        val myRace = Race().apply { name = "myRace" }
        val allRaces = listOf(myRace)
        whenever(gameSystem.allRaces).thenReturn(allRaces)
        val raceService: RaceService = mock()
        whenever(raceService.findRaceByName(myRace.name, allRaces)).thenReturn(myRace)
        whenever(gameSystem.raceService).thenReturn(raceService)

        val myClass = CharacterClass().apply { name = "myClass" }
        val allClasses = listOf(myClass)
        whenever(gameSystem.allCharacterClasses).thenReturn(allClasses)
        val characterClassService: CharacterClassService = mock()
        whenever(
            characterClassService.findClassByName(
                myClass.name,
                allClasses
            )
        ).thenReturn(myClass)
        whenever(characterClassService.getStarterPack(any(), any())).thenReturn(mock())
        whenever(gameSystem.characterClassService).thenReturn(characterClassService)

        whenever(gameSystem.itemService).thenReturn(mock())

        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(any())).thenReturn("Male")
        whenever(displayService.getDisplayAlignment(any())).thenReturn("Lawful Good")
        whenever(gameSystem.displayService).thenReturn(displayService)


        whenever(gameSystem.characterService).thenReturn(characterService)
        whenever(gameSystem.allXpTables).thenReturn(listOf(XpTable().apply { name = "myXpTable" }))

        gameSystemHolder.gameSystem = gameSystem
        gameSystemHolder.gameSystemType = GameSystemType.DND5E

        val raceScreenViewModel = createRaceScreenViewModel()
        val classScreenViewModel = createClassScreenViewModel()
        val appearanceScreenViewModel = createAppearanceViewModel()
        val characterCreatorViewModel = AbilityScoresScreenViewModel(
            gameSystemHolder,
            firebaseAnalytics
        )
        val equipmentScreenViewModel = createEquipmentScreenViewModel()

        // act
        val character = CharacterCreator().createCharacter(
            raceScreenViewModel,
            classScreenViewModel,
            appearanceScreenViewModel,
            characterCreatorViewModel,
            equipmentScreenViewModel
        )

        // assert
        assertThat(character).isNotNull
        assertAppearance(character)
        assertWeaponAttacks(character, characterService)
        verify(characterService).createCharacter(eq(character), any())
        assertEquipment(characterService, character)
        verify(characterHolder).character = character
    }

    private fun createClassScreenViewModel(): ClassScreenViewModel {
        val classScreenViewModel = ClassScreenViewModel(gameSystemHolder).apply {
            this.clazz = CharacterClass().apply { name = "myClass" }
        }
        return classScreenViewModel
    }

    private fun createAppearanceViewModel(): AppearanceScreenViewModel {
        val appearanceScreenViewModel = AppearanceScreenViewModel(gameSystemHolder).apply {
            name = "myName"
            player = "myPlayer"
            gender = "Male"
            alignment = "Lawful Good"
        }
        return appearanceScreenViewModel
    }

    private fun createRaceScreenViewModel(): RaceScreenViewModel {
        val raceScreenViewModel = RaceScreenViewModel(gameSystemHolder).apply {
            race = Race().apply { name = "myRace" }
        }
        return raceScreenViewModel
    }

    private fun createEquipmentScreenViewModel(): EquipmentScreenViewModel {
        val equipmentScreenViewModel = EquipmentScreenViewModel(gameSystemHolder).apply {
            starterPackBoxViewModels = listOf(
                StarterPackBoxViewModel(
                    createStarterBox(Weapon().apply {
                        id = 1; name = "myFirstWeapon"; weaponEncumbrance =
                        WeaponEncumbrance.ONE_HANDED
                    })
                ),
                StarterPackBoxViewModel(
                    createStarterBox(Weapon().apply {
                        id = 2; name = "mySecondWeapon"; weaponEncumbrance =
                        WeaponEncumbrance.TWO_HANDED
                    })
                ),
                StarterPackBoxViewModel(
                    createStarterBox(Armor().apply { id = 1; name = "myArmor" })
                ),
                StarterPackBoxViewModel(
                    createStarterBox(Good().apply { id = 1; name = "myGood" })
                ),
                StarterPackBoxViewModel(
                    createStarterBoxEquipmentPack()
                )
            )
        }
        return equipmentScreenViewModel
    }


    private fun assertWeaponAttacks(character: Character, characterService: CharacterService) {
        val weaponAttack = argumentCaptor<WeaponAttack>()
        verify(characterService, times(2)).createWeaponAttack(eq(character), weaponAttack.capture())

        assertThat(weaponAttack.firstValue.name).isEqualTo("myFirstWeapon")
        assertThat(weaponAttack.firstValue.attackWield).isEqualTo(AttackWield.ONE_HAND)
        assertThat(weaponAttack.secondValue.name).isEqualTo("mySecondWeapon")
        assertThat(weaponAttack.secondValue.attackWield).isEqualTo(AttackWield.TWO_HANDED)

    }

    private fun assertAppearance(character: Character) {
        assertThat(character.name).isEqualTo("myName")
        assertThat(character.player).isEqualTo("myPlayer")
        assertThat(character.race.name).isEqualTo("myRace")
        assertThat(character.classLevels[0].characterClass.name).isEqualTo("myClass")
        assertThat(character.classLevels[0].level).isEqualTo(1)
        assertThat(character.sex).isEqualTo(Sex.MALE)
        assertThat(character.alignment).isEqualTo(Alignment.LAWFUL_GOOD)
        assertThat(character.xpTable.name).isEqualTo("myXpTable")

        assertThat(character.strength).isEqualTo(10)
        assertThat(character.dexterity).isEqualTo(10)
        assertThat(character.constitution).isEqualTo(10)
        assertThat(character.intelligence).isEqualTo(10)
        assertThat(character.wisdom).isEqualTo(10)
        assertThat(character.charisma).isEqualTo(10)
        assertThat(character.imageId).isEqualTo(0)
        assertThat(character.thumbImageId).isEqualTo(1)
    }

    private fun assertEquipment(
        characterService: CharacterService,
        character: Character
    ) {
        val weapons = argumentCaptor<List<ItemGroup>>()
        verify(characterService).updateWeapons(eq(character), weapons.capture())
        assertThat(weapons.firstValue).hasSize(2)
        assertThat(weapons.firstValue[0].item).isEqualTo(Weapon().apply {
            id = 1; name = "myFirstWeapon"
        })
        assertThat(weapons.firstValue[1].item).isEqualTo(Weapon().apply {
            id = 2; name = "mySecondWeapon"
        })

        val armor = argumentCaptor<List<ItemGroup>>()
        verify(characterService).updateArmor(eq(character), armor.capture())
        assertThat(armor.firstValue).hasSize(1)
        assertThat(armor.firstValue[0].item).isEqualTo(Armor().apply { id = 1; name = "myArmor" })

        val goods = argumentCaptor<List<ItemGroup>>()
        verify(characterService).updateGoods(eq(character), goods.capture())
        assertThat(goods.firstValue).hasSize(2)
        assertThat(goods.firstValue[0].item).isEqualTo(Good().apply { id = 1; name = "myGood" })
        assertThat(goods.firstValue[1].item).isEqualTo(Good().apply { id = 2; name = "myPackGood" })
    }

    private fun createStarterBoxEquipmentPack(): StarterPackBox =
        StarterPackBox(id = 1, name = "Equipment").also {
            it.add(
                StarterPackBoxPackOption(
                    EquipmentPack(
                        id = 1,
                        name = "myPack",
                        itemGroups = mutableListOf(
                            ItemGroup().apply {
                                item = Good().apply { id = 2; name = "myPackGood" }
                            }
                        )
                    ))
            )
        }


    private fun createStarterBox(item: Item): StarterPackBox =
        StarterPackBox(id = 1, name = "StarterPackBoxItem").also {
            it.add(
                StarterPackBoxItemOption().apply {
                    add(ItemGroup().apply {
                        this.item = item
                        number = 1
                    })
                }
            )
        }

}