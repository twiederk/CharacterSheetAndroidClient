package com.android.ash.charactersheet.gui.admin.clazz;

import java.util.ArrayList;
import java.util.EnumSet;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.BaseAttackBonus;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassAbility;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.Save;
import com.d20charactersheet.framework.boc.model.Skill;

/**
 * Activity to create an character class using the CharacterClassAdministrationActivity as base.
 */
public class CharacterClassAdministrationCreateActivity extends CharacterClassAdministrationActivity {

    @Override
    protected CharacterClass createForm() {
        final CharacterClass characterClass = new CharacterClass();
        characterClass.setName("");
        characterClass.setAlignments(EnumSet.allOf(Alignment.class));
        characterClass.setHitDie(Die.D8);
        characterClass.setBaseAttackBonus(BaseAttackBonus.AVERAGE);
        characterClass.setSaves(EnumSet.noneOf(Save.class));
        characterClass.setClassAbilities(new ArrayList<ClassAbility>());
        characterClass.setSkillPointsPerLevel(4);
        characterClass.setSkills(new ArrayList<Skill>());
        return characterClass;
    }

    @Override
    protected void saveForm() {
        if (!form.getName().trim().isEmpty()) {
            characterClassService.createCharacterClass(form);
            gameSystem.clear();
        }
    }

    @Override
    protected String getHeading() {
        return getResources().getString(R.string.character_class_administration_create_title);
    }

}
