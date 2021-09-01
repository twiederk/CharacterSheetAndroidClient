package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.*
import com.d20charactersheet.framework.boc.service.*
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
import org.mockito.kotlin.*

class CharacterCreatorKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
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

        val characterCreatorViewModel = CharacterCreatorViewModel().apply {
            name = "myName"
            player = "myPlayer"
            race = Race().apply { name = "myRace" }
            this.clazz = CharacterClass().apply { name = "myClass" }
            gender = "Male"
            alignment = "Lawful Good"
            starterPackBoxViewModels = listOf(
                StarterPackBoxViewModel(
                    createStarterBox(Weapon().apply { id = 1; name = "myWeapon" })
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

        // act
        val character = CharacterCreator().createCharacter(characterCreatorViewModel)

        // assert
        assertThat(character).isNotNull
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

        verify(characterService).createCharacter(eq(character), any())

        val weapons = argumentCaptor<List<ItemGroup>>()
        verify(characterService).updateWeapons(eq(character), weapons.capture())
        assertThat(weapons.firstValue).hasSize(1)
        assertThat(weapons.firstValue[0].item).isEqualTo(Weapon().apply {
            id = 1; name = "myWeapon"
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

        verify(characterHolder).character = character
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