package com.android.ash.charactersheet.gui.main.charactercreator

import com.d20charactersheet.framework.boc.model.Alignment
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Sex

class CharacterData {

    var name = ""
    var player = ""
    var race: Race? = null
    var clazz: CharacterClass? = null
    var sex = Sex.MALE
    var alignment = Alignment.LAWFUL_GOOD

    var strength = 10
    var dexterity = 10
    var constitution = 10
    var intelligence = 10
    var wisdom = 10
    var charisma = 10

}
