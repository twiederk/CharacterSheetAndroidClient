package com.android.ash.charactersheet.gui.main.charactercreator

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.boc.service.AndroidImageService
import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Sex
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class CharacterCreatorViewModel : ViewModel(), KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterCreator: CharacterCreator by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    private val characterCreatorService = gameSystemHolder.gameSystem?.characterCreatorService
    private val ruleService = gameSystemHolder.gameSystem?.ruleService
    private val displayService = gameSystemHolder.gameSystem?.displayService

    var raceList: List<Race> = gameSystemHolder.gameSystem?.allRaces?.sortedWith(compareBy { it.name }) ?: listOf()
    var classList: List<String> = gameSystemHolder.gameSystem?.allCharacterClasses?.map { it.name }?.toList()?.sorted() ?: listOf()
    val genderList: List<String> = (Sex.values().map { displayService?.getDisplaySex(it) }.toList()) as List<String>
    val alignmentList: List<String> = (Alignment.values().map { displayService?.getDisplayAlignment(it) }.toList()) as List<String>

    var name by mutableStateOf("")
    var player by mutableStateOf("")
    var race by mutableStateOf(raceList[0])
    var clazz by mutableStateOf(classList[0])
    var gender by mutableStateOf(genderList[0])
    var alignment by mutableStateOf(alignmentList[0])

    var strength by mutableStateOf(10)
    var dexterity by mutableStateOf(10)
    var constitution by mutableStateOf(10)
    var intelligence by mutableStateOf(10)
    var wisdom by mutableStateOf(10)
    var charisma by mutableStateOf(10)

    var strengthModifier by mutableStateOf("0")
    var dexterityModifier by mutableStateOf("0")
    var constitutionModifier by mutableStateOf("0")
    var intelligenceModifier by mutableStateOf("0")
    var wisdomModifier by mutableStateOf("0")
    var charismaModifier by mutableStateOf("0")

    fun onRollDice() {
        firebaseAnalytics.logEvent(FBAnalytics.Event.STANDARD_METHOD_DICE_ROLL, null)

        strength = characterCreatorService?.rollAttributeWithStandardMethod() ?: 20
        strengthModifier = getDisplayModifier(strength)

        dexterity = gameSystemHolder.gameSystem?.characterCreatorService?.rollAttributeWithStandardMethod() ?: 20
        dexterityModifier = getDisplayModifier(dexterity)

        constitution = gameSystemHolder.gameSystem?.characterCreatorService?.rollAttributeWithStandardMethod() ?: 20
        constitutionModifier = getDisplayModifier(constitution)

        intelligence = gameSystemHolder.gameSystem?.characterCreatorService?.rollAttributeWithStandardMethod() ?: 20
        intelligenceModifier = getDisplayModifier(intelligence)

        wisdom = gameSystemHolder.gameSystem?.characterCreatorService?.rollAttributeWithStandardMethod() ?: 20
        wisdomModifier = getDisplayModifier(wisdom)

        charisma = gameSystemHolder.gameSystem?.characterCreatorService?.rollAttributeWithStandardMethod() ?: 20
        charismaModifier = getDisplayModifier(charisma)

    }

    private fun getDisplayModifier(abilityValue: Int): String {
        val attributeModifier = ruleService?.getModifier(abilityValue) ?: 0
        return displayService?.getDisplayModifier(attributeModifier) ?: "0"
    }

    fun onIncreaseStrength() {
        if (isValid(strength + 1)) {
            strength += 1
            strengthModifier = getDisplayModifier(strength)
        }
    }

    fun onIncreaseDexterity() {
        if (isValid(dexterity + 1)) {
            dexterity += 1
            dexterityModifier = getDisplayModifier(dexterity)
        }
    }

    fun onIncreaseConstitution() {
        if (isValid(constitution + 1)) {
            constitution += 1
            constitutionModifier = getDisplayModifier(constitution)
        }
    }

    fun onIncreaseIntelligence() {
        if (isValid(intelligence + 1)) {
            intelligence += 1
            intelligenceModifier = getDisplayModifier(intelligence)
        }
    }

    fun onIncreaseWisdom() {
        if (isValid(wisdom + 1)) {
            wisdom += 1
            wisdomModifier = getDisplayModifier(wisdom)
        }
    }

    fun onIncreaseCharisma() {
        if (isValid(charisma + 1)) {
            charisma += 1
            charismaModifier = getDisplayModifier(charisma)
        }
    }

    fun onDecreaseStrength() {
        if (isValid(strength - 1)) {
            strength -= 1
            strengthModifier = getDisplayModifier(strength)
        }
    }

    fun onDecreaseDexterity() {
        if (isValid(dexterity - 1)) {
            dexterity -= 1
            dexterityModifier = getDisplayModifier(dexterity)
        }
    }

    fun onDecreaseConstitution() {
        if (isValid(constitution - 1)) {
            constitution -= 1
            constitutionModifier = getDisplayModifier(constitution)
        }
    }

    fun onDecreaseIntelligence() {
        if (isValid(intelligence - 1)) {
            intelligence -= 1
            intelligenceModifier = getDisplayModifier(intelligence)
        }
    }

    fun onDecreaseWisdom() {
        if (isValid(wisdom - 1)) {
            wisdom -= 1
            wisdomModifier = getDisplayModifier(wisdom)
        }
    }

    fun onDecreaseCharisma() {
        if (isValid(charisma - 1)) {
            charisma -= 1
            charismaModifier = getDisplayModifier(charisma)
        }
    }

    private fun isValid(attributeValue: Int) = attributeValue in 1..30

    fun onCreateCharacter() {
        characterCreator.createCharacter(this)
    }

    fun onChangeName(name: String) {
        this.name = name
    }

    fun onChangePlayer(player: String) {
        this.player = player
    }

    fun onRaceChange(race: Race) {
        this.race = race
    }

    fun onClassNameChange(className: String) {
        this.clazz = className
    }

    fun onGenderChange(gender: String) {
        this.gender = gender
    }

    fun onAlignmentChange(alignment: String) {
        this.alignment = alignment
    }

    fun reset() {
        raceList = gameSystemHolder.gameSystem?.allRaces?.sortedWith(compareBy { it.name }) ?: listOf()
        classList = gameSystemHolder.gameSystem?.allCharacterClasses?.map { it.name }?.toList()?.sorted() ?: listOf()

        name = ""
        player = ""
        race = raceList[0]
        clazz = classList[0]
        gender = genderList[0]
        alignment = alignmentList[0]
        strength = 10
        strengthModifier = "0"
        dexterity = 10
        dexterityModifier = "0"
        constitution = 10
        constitutionModifier = "0"
        intelligence = 10
        intelligenceModifier = "0"
        wisdom = 10
        wisdomModifier = "0"
        charisma = 10
        charismaModifier = "0"
    }

    fun getBitmap(imageId: Int): Bitmap {
        val imageService = gameSystemHolder.gameSystem?.imageService as AndroidImageService
        return imageService.getBitmap(imageId)
    }
}