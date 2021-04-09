package com.android.ash.charactersheet.gui.main.charactercreator

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.service.AndroidDisplayServiceImpl
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Die
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.service.CharacterCreatorServiceImpl
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.DnD5eRuleServiceImpl
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import java.util.*

class CharacterCreatorViewModelKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterCreator: CharacterCreator by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<FirebaseAnalytics>()
        declareMock<CharacterCreator>()

        val gameSystem: GameSystem = mock()
        whenever(gameSystem.allRaces).thenReturn(mutableListOf(Race().apply { name = "myRace" }, Race().apply { name = "anotherRace" }))
        whenever(gameSystem.allCharacterClasses).thenReturn(listOf(CharacterClass().apply { name = "myClass" }, CharacterClass().apply { name = "anotherClass" }))
        val displayService: DisplayService = mock()
        whenever(displayService.getDisplaySex(any())).thenReturn("Male")
        whenever(displayService.getDisplayAlignment(any())).thenReturn("Lawful Good")
        whenever(gameSystem.displayService).thenReturn(displayService)

        gameSystemHolder.gameSystem = gameSystem
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun constructor_initCharacter_characterWithDefaultValue() {

        // act
        val characterCreatorViewModel = CharacterCreatorViewModel()

        // assert
        assertThat(characterCreatorViewModel.name).isEqualTo("")
        assertThat(characterCreatorViewModel.player).isEqualTo("")
        assertThat(characterCreatorViewModel.race).isEqualTo("anotherRace")
        assertThat(characterCreatorViewModel.raceList).containsExactly("anotherRace", "myRace")
        assertThat(characterCreatorViewModel.clazz).isEqualTo("anotherClass")
        assertThat(characterCreatorViewModel.classList).containsExactly("anotherClass", "myClass")
        assertThat(characterCreatorViewModel.sex).isEqualTo("Male")
        assertThat(characterCreatorViewModel.alignment).isEqualTo("Lawful Good")
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
        val underTest = CharacterCreatorViewModel()
        underTest.strength = 10

        // act
        underTest.onIncreaseStrength()

        // assert
        assertThat(underTest.strength).isEqualTo(11)
    }

    @Test
    fun onIncreaseStrength_invalidValue_strengthStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.strength = 30

        // act
        underTest.onIncreaseStrength()

        // assert
        assertThat(underTest.strength).isEqualTo(30)
    }


    @Test
    fun onDecreaseStrength_validValue_increaseStrength() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.strength = 10

        // act
        underTest.onDecreaseStrength()

        // assert
        assertThat(underTest.strength).isEqualTo(9)
    }

    @Test
    fun onDecreaseStrength_invalidValue_strengthStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.strength = 1

        // act
        underTest.onDecreaseStrength()

        // assert
        assertThat(underTest.strength).isEqualTo(1)
    }

    @Test
    fun onIncreaseDexterity_validValue_increaseDexterity() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.dexterity = 10

        // act
        underTest.onIncreaseDexterity()

        // assert
        assertThat(underTest.dexterity).isEqualTo(11)
    }

    @Test
    fun onIncreaseDexterity_invalidValue_dexterityStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.dexterity = 30

        // act
        underTest.onIncreaseDexterity()

        // assert
        assertThat(underTest.dexterity).isEqualTo(30)
    }


    @Test
    fun onDecreaseDexterity_validValue_increaseDexterity() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.dexterity = 10

        // act
        underTest.onDecreaseDexterity()

        // assert
        assertThat(underTest.dexterity).isEqualTo(9)
    }

    @Test
    fun onDecreaseDexterity_invalidValue_dexterityStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.dexterity = 1

        // act
        underTest.onDecreaseDexterity()

        // assert
        assertThat(underTest.dexterity).isEqualTo(1)
    }

    @Test
    fun onIncreaseConstitution_validValue_increaseConstitution() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.constitution = 10

        // act
        underTest.onIncreaseConstitution()

        // assert
        assertThat(underTest.constitution).isEqualTo(11)
    }

    @Test
    fun onIncreaseConstitution_invalidValue_constitutionStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.constitution = 30

        // act
        underTest.onIncreaseConstitution()

        // assert
        assertThat(underTest.constitution).isEqualTo(30)
    }


    @Test
    fun onDecreaseConstitution_validValue_increaseConstitution() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.constitution = 10

        // act
        underTest.onDecreaseConstitution()

        // assert
        assertThat(underTest.constitution).isEqualTo(9)
    }

    @Test
    fun onDecreaseConstitution_invalidValue_constitutionStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.constitution = 1

        // act
        underTest.onDecreaseConstitution()

        // assert
        assertThat(underTest.constitution).isEqualTo(1)
    }

    @Test
    fun onIncreaseIntelligence_validValue_increaseIntelligence() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.intelligence = 10

        // act
        underTest.onIncreaseIntelligence()

        // assert
        assertThat(underTest.intelligence).isEqualTo(11)
    }

    @Test
    fun onIncreaseIntelligence_invalidValue_intelligenceStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.intelligence = 30

        // act
        underTest.onIncreaseIntelligence()

        // assert
        assertThat(underTest.intelligence).isEqualTo(30)
    }


    @Test
    fun onDecreaseIntelligence_validValue_increaseIntelligence() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.intelligence = 10

        // act
        underTest.onDecreaseIntelligence()

        // assert
        assertThat(underTest.intelligence).isEqualTo(9)
    }

    @Test
    fun onDecreaseIntelligence_invalidValue_intelligenceStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.intelligence = 1

        // act
        underTest.onDecreaseIntelligence()

        // assert
        assertThat(underTest.intelligence).isEqualTo(1)
    }

    @Test
    fun onIncreaseWisdom_validValue_increaseWisdom() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.wisdom = 10

        // act
        underTest.onIncreaseWisdom()

        // assert
        assertThat(underTest.wisdom).isEqualTo(11)
    }

    @Test
    fun onIncreaseWisdom_invalidValue_wisdomStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.wisdom = 30

        // act
        underTest.onIncreaseWisdom()

        // assert
        assertThat(underTest.wisdom).isEqualTo(30)
    }


    @Test
    fun onDecreaseWisdom_validValue_increaseWisdom() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.wisdom = 10

        // act
        underTest.onDecreaseWisdom()

        // assert
        assertThat(underTest.wisdom).isEqualTo(9)
    }

    @Test
    fun onDecreaseWisdom_invalidValue_wisdomStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.wisdom = 1

        // act
        underTest.onDecreaseWisdom()

        // assert
        assertThat(underTest.wisdom).isEqualTo(1)
    }

    @Test
    fun onIncreaseCharisma_validValue_increaseCharisma() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.charisma = 10

        // act
        underTest.onIncreaseCharisma()

        // assert
        assertThat(underTest.charisma).isEqualTo(11)
    }

    @Test
    fun onIncreaseCharisma_invalidValue_charismaStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.charisma = 30

        // act
        underTest.onIncreaseCharisma()

        // assert
        assertThat(underTest.charisma).isEqualTo(30)
    }


    @Test
    fun onDecreaseCharisma_validValue_increaseCharisma() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.charisma = 10

        // act
        underTest.onDecreaseCharisma()

        // assert
        assertThat(underTest.charisma).isEqualTo(9)
    }

    @Test
    fun onDecreaseCharisma_invalidValue_charismaStaysUnchanged() {
        // arrange
        val underTest = CharacterCreatorViewModel()
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

        val gameSystem = gameSystemHolder.gameSystem
        whenever(gameSystem?.characterCreatorService).thenReturn(CharacterCreatorServiceImpl())
        whenever(gameSystem?.ruleService).thenReturn(DnD5eRuleServiceImpl())
        whenever(gameSystem?.displayService).thenReturn(AndroidDisplayServiceImpl(mock()))
        gameSystemHolder.gameSystem = gameSystem

        val underTest = CharacterCreatorViewModel()

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
    fun onCreateCharacter_everythingIsFine_createNewCharacter() {

        // act
        CharacterCreatorViewModel().onCreateCharacter()

        // assert
        verify(characterCreator, only()).createCharacter(any())
    }

    @Test
    fun reset_resetProperties_allPropertiesAreReset() {
        // arrange
        val underTest = CharacterCreatorViewModel()
        underTest.raceList = listOf("firstRace", "secondRace")
        underTest.classList = listOf("firstClass", "secondClass")

        underTest.name = "myName"
        underTest.player = "myPlayer"
        underTest.race = underTest.raceList[1]
        underTest.clazz = underTest.classList[1]
        underTest.sex = "Female"
        underTest.alignment = "Neutral"
        underTest.onRollDice()

        // act
        underTest.reset()

        // assert
        assertThat(underTest.name).isEqualTo("")
        assertThat(underTest.player).isEqualTo("")
        assertThat(underTest.race).isEqualTo("anotherRace")
        assertThat(underTest.clazz).isEqualTo("anotherClass")
        assertThat(underTest.sex).isEqualTo("Male")
        assertThat(underTest.alignment).isEqualTo("Lawful Good")
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