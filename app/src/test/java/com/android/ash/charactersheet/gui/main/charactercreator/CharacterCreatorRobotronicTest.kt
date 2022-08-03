package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AbilityScoresScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AppearanceScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.ClassScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.EquipmentScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.RaceScreenViewModel
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.ClassLevel
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class CharacterCreatorRobotronicTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val raceScreenViewModel: RaceScreenViewModel by inject()
    private val classScreenViewModel: ClassScreenViewModel by inject()
    private val appearanceScreenViewModel: AppearanceScreenViewModel by inject()
    private val abilityScoresScreenViewModel: AbilityScoresScreenViewModel by inject()
    private val equipmentScreenViewModel: EquipmentScreenViewModel by inject()
    private val gameSystemHolder: GameSystemHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<FirebaseAnalytics>()
        declareMock<RaceScreenViewModel>()
        declareMock<ClassScreenViewModel>()
        declareMock<AppearanceScreenViewModel>()
        declareMock<AbilityScoresScreenViewModel>()
        declareMock<EquipmentScreenViewModel>()
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun createCharacter_everythingIsFine_createCharacterEventIsLoggedInFirebase() {

        // arrange
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.characterService).thenReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        val characterCreatorAppearance: CharacterCreatorAppearance = mock()
        whenever(
            characterCreatorAppearance.fillAppearance(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        ).thenReturn(
            Character().apply {
                race = Race().apply { name = "myRace" }
                classLevels = mutableListOf(ClassLevel(CharacterClass().apply { name = "myClass" }))
            }
        )

        // act
        CharacterCreator(
            characterCreatorAppearance = characterCreatorAppearance,
            characterCreatorEquipment = mock(),
            characterCreatorWeaponAttack = mock()
        ).createCharacter(
            raceScreenViewModel,
            classScreenViewModel,
            appearanceScreenViewModel,
            abilityScoresScreenViewModel,
            equipmentScreenViewModel
        )

        // Assert
        val captor = argumentCaptor<Bundle>()
        verify(firebaseAnalytics).logEvent(eq("character_create"), captor.capture())
        assertThat(captor.firstValue.getString("race_name")).isEqualTo("myRace")
        assertThat(captor.firstValue.getString("class_name")).isEqualTo("myClass")

    }

}