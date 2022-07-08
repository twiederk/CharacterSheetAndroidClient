package com.android.ash.charactersheet.gui

import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.billing.MessageDisplay
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.gui.main.charactercreator.CharacterCreator
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AbilityScoresScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AppearanceScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.ClassScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.EquipmentScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.RaceScreenViewModel
import com.android.ash.charactersheet.gui.main.characterlist.CharacterListViewModel
import com.android.ash.charactersheet.gui.sheet.FragmentPagerFactory
import com.android.ash.charactersheet.gui.sheet.SheetPanelFactory
import com.android.ash.charactersheet.gui.sheet.skill.SkillEditArrayAdapterFactory
import com.d20charactersheet.framework.boc.model.Armor
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.EquipmentPack
import com.d20charactersheet.framework.boc.model.Good
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.StarterPack
import com.d20charactersheet.framework.boc.model.StarterPackBox
import com.d20charactersheet.framework.boc.model.StarterPackBoxItemOption
import com.d20charactersheet.framework.boc.model.Weapon
import com.d20charactersheet.framework.boc.service.CharacterClassService
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.ItemService
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class KoinConfigurationInstrumentedTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val billing: Billing by inject()
    private val messageDisplay: MessageDisplay by inject()
    private val characterCreator: CharacterCreator by inject()
    private val sheetPanelFactory: SheetPanelFactory by inject()
    private val fragmentPagerFactory: FragmentPagerFactory by inject()
    private val raceScreenViewModel: RaceScreenViewModel by inject()
    private val classScreenViewModel: ClassScreenViewModel by inject()
    private val appearanceScreenViewModel: AppearanceScreenViewModel by inject()
    private val abilityScoresScreenViewModel: AbilityScoresScreenViewModel by inject()
    private val equipmentScreenViewModel: EquipmentScreenViewModel by inject()
    private val characterListViewModel: CharacterListViewModel by inject()
    private val skillEditArrayAdapterFactory: SkillEditArrayAdapterFactory by inject()

    @Before
    fun before() {
        val gameSystem: GameSystem = mock()

        // RaceScreenViewModel
        whenever(gameSystem.allRaces).thenReturn(listOf(Race().apply { name = "myRace" }))

        // ClassScreenViewModel
        whenever(gameSystem.allCharacterClasses).thenReturn(listOf(CharacterClass().apply {
            name = "myClass"
        }))

        // AppearanceScreenViewModel
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(any())).thenReturn("Male")
        whenever(displayService.getDisplayAlignment(any())).thenReturn("Lawful Good")
        whenever(gameSystem.displayService).thenReturn(displayService)

        // AbilityScoresScreenViewModel
        whenever(gameSystem.ruleService).thenReturn(mock())
        whenever(gameSystem.characterCreatorService).thenReturn(mock())

        // EquipmentScreenViewModel
        val characterClassService: CharacterClassService = mock()
        whenever(gameSystem.allWeapons).thenReturn(listOf(Weapon().apply { name = "myWeapon" }))
        whenever(gameSystem.allArmor).thenReturn(listOf(Armor().apply { name = "myArmor" }))
        whenever(gameSystem.allGoods).thenReturn(listOf(Good().apply { name = "myGood" }))
        whenever(gameSystem.allEquipmentPacks).thenReturn(
            listOf(
                EquipmentPack(
                    id = 1,
                    name = "myPack",
                    itemGroups = mutableListOf()
                )
            )
        )

        whenever(characterClassService.getStarterPack(any(), any())).thenReturn(
            StarterPack().also { starterPack ->
                starterPack.add(StarterPackBox().also { it.add(StarterPackBoxItemOption()) })
            }
        )

        val itemService: ItemService = mock()
        whenever(gameSystem.characterClassService).thenReturn(characterClassService)
        whenever(gameSystem.itemService).thenReturn(itemService)

        gameSystemHolder.gameSystem = gameSystem
        gameSystemHolder.gameSystemType = GameSystemType.DND5E
    }

    @Test
    fun koinConfiguration() {
        assertThat(gameSystemHolder).isNotNull
        assertThat(preferenceServiceHolder).isNotNull
        assertThat(characterHolder).isNotNull
        assertThat(firebaseAnalytics).isNotNull
        assertThat(billing).isNotNull
        assertThat(messageDisplay).isNotNull
        assertThat(characterCreator).isNotNull
        assertThat(sheetPanelFactory).isNotNull
        assertThat(fragmentPagerFactory).isNotNull
        assertThat(raceScreenViewModel).isNotNull
        assertThat(classScreenViewModel).isNotNull
        assertThat(appearanceScreenViewModel).isNotNull
        assertThat(abilityScoresScreenViewModel).isNotNull
        assertThat(equipmentScreenViewModel).isNotNull
        assertThat(characterListViewModel).isNotNull
        assertThat(skillEditArrayAdapterFactory).isNotNull
    }

}