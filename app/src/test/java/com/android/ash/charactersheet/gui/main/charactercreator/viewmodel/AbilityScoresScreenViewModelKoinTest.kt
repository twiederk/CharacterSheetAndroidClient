package com.android.ash.charactersheet.gui.main.charactercreator.viewmodel

import android.content.res.Resources
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.service.AndroidDisplayServiceImpl
import com.d20charactersheet.framework.boc.model.Die
import com.d20charactersheet.framework.boc.service.CharacterCreatorServiceImpl
import com.d20charactersheet.framework.boc.service.DnD5eRuleServiceImpl
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
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
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

class AbilityScoresScreenViewModelKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

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
        declareMock<FirebaseAnalytics>()

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.displayService).thenReturn(mock())
        whenever(gameSystem.ruleService).thenReturn(mock())
        whenever(gameSystem.characterCreatorService).thenReturn(mock())

        gameSystemHolder.gameSystem = gameSystem
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun constructor_initCharacter_characterWithDefaultValue() {

        // act
        val characterCreatorViewModel = AbilityScoresScreenViewModel(
            gameSystemHolder,
            firebaseAnalytics
        )

        // assert
        assertThat(characterCreatorViewModel.strength).isEqualTo(10)
        assertThat(characterCreatorViewModel.dexterity).isEqualTo(10)
        assertThat(characterCreatorViewModel.constitution).isEqualTo(10)
        assertThat(characterCreatorViewModel.intelligence).isEqualTo(10)
        assertThat(characterCreatorViewModel.wisdom).isEqualTo(10)
        assertThat(characterCreatorViewModel.charisma).isEqualTo(10)
    }

    @Test
    fun onIncreaseStrength_validValue_increaseStrength() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.strength = 10

        // act
        underTest.onIncreaseStrength()

        // assert
        assertThat(underTest.strength).isEqualTo(11)
    }

    @Test
    fun onIncreaseStrength_invalidValue_strengthStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.strength = 30

        // act
        underTest.onIncreaseStrength()

        // assert
        assertThat(underTest.strength).isEqualTo(30)
    }


    @Test
    fun onDecreaseStrength_validValue_increaseStrength() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.strength = 10

        // act
        underTest.onDecreaseStrength()

        // assert
        assertThat(underTest.strength).isEqualTo(9)
    }

    @Test
    fun onDecreaseStrength_invalidValue_strengthStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.strength = 1

        // act
        underTest.onDecreaseStrength()

        // assert
        assertThat(underTest.strength).isEqualTo(1)
    }

    @Test
    fun onIncreaseDexterity_validValue_increaseDexterity() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.dexterity = 10

        // act
        underTest.onIncreaseDexterity()

        // assert
        assertThat(underTest.dexterity).isEqualTo(11)
    }

    @Test
    fun onIncreaseDexterity_invalidValue_dexterityStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.dexterity = 30

        // act
        underTest.onIncreaseDexterity()

        // assert
        assertThat(underTest.dexterity).isEqualTo(30)
    }


    @Test
    fun onDecreaseDexterity_validValue_increaseDexterity() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.dexterity = 10

        // act
        underTest.onDecreaseDexterity()

        // assert
        assertThat(underTest.dexterity).isEqualTo(9)
    }

    @Test
    fun onDecreaseDexterity_invalidValue_dexterityStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.dexterity = 1

        // act
        underTest.onDecreaseDexterity()

        // assert
        assertThat(underTest.dexterity).isEqualTo(1)
    }

    @Test
    fun onIncreaseConstitution_validValue_increaseConstitution() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.constitution = 10

        // act
        underTest.onIncreaseConstitution()

        // assert
        assertThat(underTest.constitution).isEqualTo(11)
    }

    @Test
    fun onIncreaseConstitution_invalidValue_constitutionStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.constitution = 30

        // act
        underTest.onIncreaseConstitution()

        // assert
        assertThat(underTest.constitution).isEqualTo(30)
    }


    @Test
    fun onDecreaseConstitution_validValue_increaseConstitution() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.constitution = 10

        // act
        underTest.onDecreaseConstitution()

        // assert
        assertThat(underTest.constitution).isEqualTo(9)
    }

    @Test
    fun onDecreaseConstitution_invalidValue_constitutionStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.constitution = 1

        // act
        underTest.onDecreaseConstitution()

        // assert
        assertThat(underTest.constitution).isEqualTo(1)
    }

    @Test
    fun onIncreaseIntelligence_validValue_increaseIntelligence() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.intelligence = 10

        // act
        underTest.onIncreaseIntelligence()

        // assert
        assertThat(underTest.intelligence).isEqualTo(11)
    }

    @Test
    fun onIncreaseIntelligence_invalidValue_intelligenceStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.intelligence = 30

        // act
        underTest.onIncreaseIntelligence()

        // assert
        assertThat(underTest.intelligence).isEqualTo(30)
    }


    @Test
    fun onDecreaseIntelligence_validValue_increaseIntelligence() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.intelligence = 10

        // act
        underTest.onDecreaseIntelligence()

        // assert
        assertThat(underTest.intelligence).isEqualTo(9)
    }

    @Test
    fun onDecreaseIntelligence_invalidValue_intelligenceStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.intelligence = 1

        // act
        underTest.onDecreaseIntelligence()

        // assert
        assertThat(underTest.intelligence).isEqualTo(1)
    }

    @Test
    fun onIncreaseWisdom_validValue_increaseWisdom() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.wisdom = 10

        // act
        underTest.onIncreaseWisdom()

        // assert
        assertThat(underTest.wisdom).isEqualTo(11)
    }

    @Test
    fun onIncreaseWisdom_invalidValue_wisdomStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.wisdom = 30

        // act
        underTest.onIncreaseWisdom()

        // assert
        assertThat(underTest.wisdom).isEqualTo(30)
    }


    @Test
    fun onDecreaseWisdom_validValue_increaseWisdom() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.wisdom = 10

        // act
        underTest.onDecreaseWisdom()

        // assert
        assertThat(underTest.wisdom).isEqualTo(9)
    }

    @Test
    fun onDecreaseWisdom_invalidValue_wisdomStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.wisdom = 1

        // act
        underTest.onDecreaseWisdom()

        // assert
        assertThat(underTest.wisdom).isEqualTo(1)
    }

    @Test
    fun onIncreaseCharisma_validValue_increaseCharisma() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.charisma = 10

        // act
        underTest.onIncreaseCharisma()

        // assert
        assertThat(underTest.charisma).isEqualTo(11)
    }

    @Test
    fun onIncreaseCharisma_invalidValue_charismaStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.charisma = 30

        // act
        underTest.onIncreaseCharisma()

        // assert
        assertThat(underTest.charisma).isEqualTo(30)
    }


    @Test
    fun onDecreaseCharisma_validValue_increaseCharisma() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.charisma = 10

        // act
        underTest.onDecreaseCharisma()

        // assert
        assertThat(underTest.charisma).isEqualTo(9)
    }

    @Test
    fun onDecreaseCharisma_invalidValue_charismaStaysUnchanged() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.charisma = 1

        // act
        underTest.onDecreaseCharisma()

        // assert
        assertThat(underTest.charisma).isEqualTo(1)
    }

    @Test
    fun onRollDice() {
        // arrange
        Die.setRandom(Random(1))

        val gameSystem = checkNotNull(gameSystemHolder.gameSystem)
        whenever(gameSystem.characterCreatorService).thenReturn(CharacterCreatorServiceImpl())
        whenever(gameSystem.ruleService).thenReturn(DnD5eRuleServiceImpl())
        val resources: Resources = mock()
        whenever(resources.getString(any())).thenReturn("")
        whenever(gameSystem.displayService).thenReturn(AndroidDisplayServiceImpl(resources))
        gameSystemHolder.gameSystem = gameSystem

        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)

        // act
        underTest.onRollDice()

        // assert
        assertThat(underTest.strength).isEqualTo(13)
        assertThat(underTest.strengthModifier).isEqualTo("+1")
        assertThat(underTest.dexterity).isEqualTo(13)
        assertThat(underTest.dexterityModifier).isEqualTo("+1")
        assertThat(underTest.constitution).isEqualTo(12)
        assertThat(underTest.constitutionModifier).isEqualTo("+1")
        assertThat(underTest.intelligence).isEqualTo(11)
        assertThat(underTest.intelligenceModifier).isEqualTo("0")
        assertThat(underTest.wisdom).isEqualTo(10)
        assertThat(underTest.wisdomModifier).isEqualTo("0")
        assertThat(underTest.charisma).isEqualTo(14)
        assertThat(underTest.charismaModifier).isEqualTo("+2")

        // tear down
        Die.setRandom(Random())
    }

    @Test
    fun reset_resetProperties_allPropertiesAreReset() {
        // arrange
        val underTest = AbilityScoresScreenViewModel(gameSystemHolder, firebaseAnalytics)
        underTest.onRollDice()

        // act
        underTest.reset()

        // assert
        assertThat(underTest.strength).isEqualTo(10)
        assertThat(underTest.strengthModifier).isEqualTo("0")
        assertThat(underTest.dexterity).isEqualTo(10)
        assertThat(underTest.dexterityModifier).isEqualTo("0")
        assertThat(underTest.constitution).isEqualTo(10)
        assertThat(underTest.constitutionModifier).isEqualTo("0")
        assertThat(underTest.intelligence).isEqualTo(10)
        assertThat(underTest.intelligenceModifier).isEqualTo("0")
        assertThat(underTest.wisdom).isEqualTo(10)
        assertThat(underTest.wisdomModifier).isEqualTo("0")
        assertThat(underTest.charisma).isEqualTo(10)
        assertThat(underTest.charismaModifier).isEqualTo("0")
    }


}