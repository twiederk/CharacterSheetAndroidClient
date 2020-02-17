package com.android.ash.charactersheet.gui.admin.clazz.skill;

import com.d20charactersheet.framework.boc.model.Skill;

import androidx.annotation.NonNull;

/**
 * The skill model stores if a skill is a class skill or not.
 */
class SkillModel {

    private final Skill skill;
    private boolean classSkill;

    /**
     * Creates skill model of the given skill and state.
     * 
     * @param skill
     *            The skill.
     * @param classSkill
     *            True, if the skill is a class skill.
     */
    public SkillModel(final Skill skill, final boolean classSkill) {
        this.skill = skill;
        this.classSkill = classSkill;
    }

    /**
     * Returns the skill.
     * 
     * @return The skill.
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Returns true, if the skill is a class skill.
     * 
     * @return True, if the skill is a class skill.
     */
    public boolean isClassSkill() {
        return classSkill;
    }

    /**
     * Set to true if skill is a class skill.
     * 
     * @param classSkill
     *            True, if skill is a class skill.
     */
    public void setClassSkill(final boolean classSkill) {
        this.classSkill = classSkill;
    }

    @NonNull
    @Override
    public String toString() {
        return skill.getName() + ": " + classSkill;
    }

}
