package com.android.ash.charactersheet.gui.admin.clazz;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndPositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.BaseAttackBonus;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassAbility;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.Save;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.util.ClassAbilityLevelNameComparator;
import com.d20charactersheet.framework.boc.util.SkillComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

/**
 * Helper to the CharacterClassAdministrationActivity. Helps to create the views, sets and retrieves the models of the
 * views.
 */
class CharacterClassAdministrationHelper {

    private final Activity activity;
    private final CharacterClass characterClass;
    private final DisplayService displayService;

    private NumberViewController skillPointsController;

    private EnumSet<Alignment> alignments;
    private List<ClassAbility> classAbilities;
    private List<Skill> skills;
    private List<ClassAbility> deletedClassAbilities;

    /**
     * Creates CharacterClassAdministrationHelper with the given activity, character class and display service.
     * 
     * @param activity
     *            The activity to get the views from.
     * @param characterClass
     *            The character class to administer.
     * @param displayService
     *            The display service to display data properly.
     */
    CharacterClassAdministrationHelper(final Activity activity, final CharacterClass characterClass,
                                       final DisplayService displayService) {
        this.activity = activity;
        this.characterClass = characterClass;
        this.displayService = displayService;
    }

    /**
     * Creates the view for name, alignments, hit die, base attack bonus, high saves, skill points per level and class
     * skills.
     */
    void createViews() {
        createName();
        createAlignments();
        createHitDieSpinner();
        createBaseAttackBonusSpinner();
        createSaves();
        createClassAbilities();
        createSkillPointsPerLevel();
        createSkills();
    }

    private void createName() {
        final TextView nameTextView = activity.findViewById(R.id.character_class_administration_name);
        nameTextView.setText(characterClass.getName());
    }

    private void createAlignments() {
        alignments = EnumSet.copyOf(characterClass.getAlignments());
    }

    private void createHitDieSpinner() {
        final ArrayAdapter<Die> hitDieArrayAdapter = new HitDieArrayAdapter(activity, displayService,
                new ArrayList<>(Die.HIT_DICE));
        final Spinner hitDieSpinner = activity.findViewById(R.id.character_class_administration_hitdie);
        hitDieSpinner.setAdapter(hitDieArrayAdapter);
        hitDieSpinner.setSelection(characterClass.getHitDie().ordinal() - 2);
    }

    private void createBaseAttackBonusSpinner() {
        final ArrayAdapter<BaseAttackBonus> baseAttackBonusArrayAdapter = new BaseAttackBonusArrayAdapter(activity,
                displayService, Arrays.asList(BaseAttackBonus.values()));
        setSpinner(baseAttackBonusArrayAdapter, characterClass
                .getBaseAttackBonus().ordinal());
    }

    private void createSaves() {
        setCheckBox(characterClass.getSaves().contains(Save.WILL), R.id.character_class_administration_save_will);
        setCheckBox(characterClass.getSaves().contains(Save.REFLEX), R.id.character_class_administration_save_reflex);
        setCheckBox(characterClass.getSaves().contains(Save.FORTITUDE),
                R.id.character_class_administration_save_fortitide);

    }

    private void createClassAbilities() {
        classAbilities = new ArrayList<>(characterClass.getClassAbilities());
    }

    private void createSkillPointsPerLevel() {
        final StepNumberView numberView = activity
                .findViewById(R.id.character_class_administration_skillpoints);
        skillPointsController = new ZeroAndPositiveNumberViewController(characterClass.getSkillPointsPerLevel());
        numberView.setController(skillPointsController);
    }

    private void createSkills() {
        skills = new ArrayList<>(characterClass.getSkills());
    }

    private void setSpinner(final SpinnerAdapter adapter, final int position) {
        final Spinner attributeSpinner = activity.findViewById(R.id.character_class_administration_baseattackbonus);
        attributeSpinner.setAdapter(adapter);
        attributeSpinner.setSelection(position);
    }

    private void setCheckBox(final boolean checked, final int checkBoxResourceId) {
        final CheckBox checkBox = activity.findViewById(checkBoxResourceId);
        checkBox.setChecked(checked);
    }

    /**
     * Returns the alignments of the character class.
     * 
     * @return The alignments of the character class.
     */
    public EnumSet<Alignment> getAlignments() {
        return alignments;
    }

    /**
     * Sets the alignments of the character class.
     * 
     * @param alignments
     *            The alignments of the character class.
     */
    public void setAlignments(final EnumSet<Alignment> alignments) {
        this.alignments = alignments;
    }

    /**
     * Returns all abilities of the class.
     * 
     * @return All abilities of the class.
     */
    public List<ClassAbility> getClassAbilities() {
        return classAbilities;
    }

    /**
     * Sets the abilities of class.
     * 
     * @param classAbilities
     *            The abilities of the class.
     */
    public void setClassAbilities(final List<ClassAbility> classAbilities) {
        this.classAbilities = classAbilities;
    }

    /**
     * Returns the class skills.
     * 
     * @return The class skills.
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     * Sets the class skills.
     * 
     * @param skills
     *            The class skills.
     */
    public void setSkills(final List<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Returns the skill points per level.
     * 
     * @return The skill points per level.
     */
    int getSkillPointsPerLevel() {
        return skillPointsController.getNumber().intValue();
    }

    /**
     * Returns the text of the alignments to display.
     * 
     * @return The text of the alignments to display.
     */
    String getAlignmentsText() {
        final StringBuilder builder = new StringBuilder();
        for (final Iterator<Alignment> iterator = alignments.iterator(); iterator.hasNext();) {
            final Alignment alignment = iterator.next();
            builder.append(displayService.getDisplayAlignment(alignment));
            if (iterator.hasNext()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Returns the text to display the class skills.
     * 
     * @return The text to display the class skills.
     */
    String getSkillText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(activity.getResources().getString(R.string.character_class_administration_skills_label));
        builder.append(":\n");
        Collections.sort(skills, new SkillComparator());
        for (final Iterator<Skill> iterator = skills.iterator(); iterator.hasNext();) {
            final Skill skill = iterator.next();
            builder.append(skill.getName());
            if (iterator.hasNext()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Creates the text to display the abilities in the character class formular. After a heading each ability with its
     * name and level in brackets is displayed on a separate line.
     * 
     * @return The text to display the abilities in the character class formular.
     */
    String getClassAbilityText() {
        Collections.sort(classAbilities, new ClassAbilityLevelNameComparator());
        final StringBuilder builder = new StringBuilder();
        builder.append(activity.getResources().getString(R.string.character_class_administration_abilities_label));
        builder.append(":\n");
        for (final Iterator<ClassAbility> iterator = classAbilities.iterator(); iterator.hasNext();) {
            final ClassAbility classAbility = iterator.next();
            builder.append(classAbility.getAbility().getName());
            builder.append(" (");
            builder.append(classAbility.getLevel());
            builder.append(")");
            if (iterator.hasNext()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Sets class abilities to delete.
     * 
     * @param deletedClassAbilities
     *            The class abilities to delete.
     */
    void setDeletedClassAbilities(final List<ClassAbility> deletedClassAbilities) {
        this.deletedClassAbilities = deletedClassAbilities;
    }

    /**
     * Returns list of class abilities to delete.
     * 
     * @return List of class abilities to delete.
     */
    List<ClassAbility> getDeletedClassAbilities() {
        return deletedClassAbilities;
    }

}
