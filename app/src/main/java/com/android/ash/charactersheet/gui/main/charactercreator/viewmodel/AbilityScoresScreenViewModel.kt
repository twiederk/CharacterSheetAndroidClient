package com.android.ash.charactersheet.gui.main.charactercreator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinComponent

class AbilityScoresScreenViewModel(
    val gameSystemHolder: GameSystemHolder,
    val firebaseAnalytics: FirebaseAnalytics
) : ViewModel(), KoinComponent {

    private var gameSystem = checkNotNull(this.gameSystemHolder.gameSystem)

    private var characterCreatorService = gameSystem.characterCreatorService
    private var characterClassService = gameSystem.characterClassService
    private var ruleService = gameSystem.ruleService
    private var displayService = gameSystem.displayService

    var strength by mutableIntStateOf(10)
    var dexterity by mutableIntStateOf(10)
    var constitution by mutableIntStateOf(10)
    var intelligence by mutableIntStateOf(10)
    var wisdom by mutableIntStateOf(10)
    var charisma by mutableIntStateOf(10)

    var strengthModifier by mutableStateOf("0")
    var dexterityModifier by mutableStateOf("0")
    var constitutionModifier by mutableStateOf("0")
    var intelligenceModifier by mutableStateOf("0")
    var wisdomModifier by mutableStateOf("0")
    var charismaModifier by mutableStateOf("0")


    fun onRollDice() {
        this.firebaseAnalytics.logEvent(FBAnalytics.Event.STANDARD_METHOD_DICE_ROLL, null)

        strength = characterCreatorService.rollAttributeWithStandardMethod()
        strengthModifier = getDisplayModifier(strength)

        dexterity = gameSystem.characterCreatorService.rollAttributeWithStandardMethod()
        dexterityModifier = getDisplayModifier(dexterity)

        constitution = gameSystem.characterCreatorService.rollAttributeWithStandardMethod()
        constitutionModifier = getDisplayModifier(constitution)

        intelligence = gameSystem.characterCreatorService.rollAttributeWithStandardMethod()
        intelligenceModifier = getDisplayModifier(intelligence)

        wisdom = gameSystem.characterCreatorService.rollAttributeWithStandardMethod()
        wisdomModifier = getDisplayModifier(wisdom)

        charisma = gameSystem.characterCreatorService.rollAttributeWithStandardMethod()
        charismaModifier = getDisplayModifier(charisma)

    }

    private fun getDisplayModifier(abilityValue: Int): String {
        val attributeModifier = ruleService.getModifier(abilityValue)
        return displayService.getDisplayModifier(attributeModifier) ?: "0"
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


    fun reset() {
        gameSystem = checkNotNull(this.gameSystemHolder.gameSystem)

        characterCreatorService = gameSystem.characterCreatorService
        characterClassService = gameSystem.characterClassService
        ruleService = gameSystem.ruleService
        displayService = gameSystem.displayService

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

}