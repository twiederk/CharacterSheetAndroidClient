package com.android.ash.charactersheet.gui.admin.clazz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.clazz.ability.ClassAdministrationAbilityListActivity;
import com.android.ash.charactersheet.gui.admin.clazz.alignment.CharacterClassAlignmentActivity;
import com.android.ash.charactersheet.gui.admin.clazz.skill.CharacterClassSkillActivity;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.android.ash.charactersheet.gui.util.IntentAndResultOnClickListener;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.BaseAttackBonus;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassAbility;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.Save;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.service.AbilityService;
import com.d20charactersheet.framework.boc.service.CharacterClassService;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.SkillService;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * The form to create or edit an character class. It contains the name, alignments, hit die, base attack bonus, high
 * saves, skill points per level and class skills to administer.
 */
public abstract class CharacterClassAdministrationActivity extends FormActivity<CharacterClass> {

    /**
     * Request code of the CharacterClassAlignmentActivity
     */
    private static final int REQUEST_CODE_ALIGNMENT = 0;

    /**
     * Request code of the CharacterClassSkillActivity
     */
    private static final int REQUEST_CODE_SKILL = 1;

    /**
     * Request code of the CharacterClassAbilityActivity
     */
    private static final int REQUEST_CODE_ABILITY = 2;

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    GameSystem gameSystem;
    CharacterClassService characterClassService;
    CharacterService characterService;

    CharacterClassAdministrationHelper helper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        helper = new CharacterClassAdministrationHelper(this, form, displayService);
        helper.createViews();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.character_class_administration;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
        characterClassService = Objects.requireNonNull(gameSystem).getCharacterClassService();
        characterService = gameSystem.getCharacterService();
    }


    @Override
    protected void fillViews() {
        resumeAlignments();
        resumeClassAbilities();
        resumeSkills();
    }

    private void resumeAlignments() {
        final String alignmentsText = helper.getAlignmentsText();
        setText(alignmentsText, R.id.character_class_administration_alignments);

        final View alignmentView = findViewById(R.id.character_class_administration_alignments);
        final Intent intent = new Intent(this, CharacterClassAlignmentActivity.class);
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, helper.getAlignments());
        alignmentView.setOnClickListener(new IntentAndResultOnClickListener(this, intent,
                CharacterClassAdministrationActivity.REQUEST_CODE_ALIGNMENT));

    }

    private void resumeClassAbilities() {
        final String classAbilityText = helper.getClassAbilityText();
        setText(classAbilityText, R.id.character_class_administration_abilities);

        final View classAbilityView = findViewById(R.id.character_class_administration_abilities);
        final Intent intent = new Intent(this, ClassAdministrationAbilityListActivity.class);
        final List<ClassAbility> classAbilities = helper.getClassAbilities();
        final int[] classAbilitiesData = new int[classAbilities.size() * 2];
        for (int i = 0; i < classAbilities.size(); i++) {
            final ClassAbility classAbility = classAbilities.get(i);
            classAbilitiesData[i * 2] = classAbility.getAbility().getId();
            classAbilitiesData[i * 2 + 1] = classAbility.getLevel();
        }
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, classAbilitiesData);
        classAbilityView.setOnClickListener(new IntentAndResultOnClickListener(this, intent,
                CharacterClassAdministrationActivity.REQUEST_CODE_ABILITY));

    }

    private void resumeSkills() {
        final String skillText = helper.getSkillText();
        setText(skillText, R.id.character_class_administration_skills);

        final View skillView = findViewById(R.id.character_class_administration_skills);
        final Intent intent = new Intent(this, CharacterClassSkillActivity.class);
        final List<Skill> skills = helper.getSkills();
        final int[] skillIds = new int[skills.size()];
        for (int i = 0; i < skillIds.length; i++) {
            skillIds[i] = skills.get(i).getId();
        }
        intent.putExtra(INTENT_EXTRA_DATA_OBJECT, skillIds);
        skillView.setOnClickListener(new IntentAndResultOnClickListener(this, intent,
                CharacterClassAdministrationActivity.REQUEST_CODE_SKILL));

    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.character_class_administration_name));
        form.setAlignments(helper.getAlignments());
        form.setHitDie((Die) getSelectedItemOfSpinner(R.id.character_class_administration_hitdie));
        form.setBaseAttackBonus((BaseAttackBonus) //
                getSelectedItemOfSpinner(R.id.character_class_administration_baseattackbonus));
        form.setSaves(getSaves());
        final List<ClassAbility> deletedClassAbilities = getDeletedClassAbilities();
        helper.setDeletedClassAbilities(deletedClassAbilities);
        form.setClassAbilities(helper.getClassAbilities());
        form.setSkillPointsPerLevel(helper.getSkillPointsPerLevel());
        form.setSkills(helper.getSkills());
    }

    private List<ClassAbility> getDeletedClassAbilities() {
        final List<ClassAbility> deletedClassAbilities = new LinkedList<>();
        final List<ClassAbility> newClassAbilities = helper.getClassAbilities();
        for (final ClassAbility oldClassAbility : form.getClassAbilities()) {
            if (!contains(newClassAbilities, oldClassAbility)) {
                deletedClassAbilities.add(oldClassAbility);
            }

        }
        return deletedClassAbilities;
    }

    private boolean contains(final List<ClassAbility> newClassAbilities, final ClassAbility oldClassAbility) {
        for (final ClassAbility newClassAbility : newClassAbilities) {
            if (newClassAbility.getAbility().getId() == oldClassAbility.getAbility().getId()) {
                return true;
            }
        }
        return false;
    }

    private EnumSet<Save> getSaves() {
        final EnumSet<Save> saves = EnumSet.noneOf(Save.class);
        final int[] resourceIds = {R.id.character_class_administration_save_fortitide,
                R.id.character_class_administration_save_reflex, R.id.character_class_administration_save_will};
        for (final Save save : Save.values()) {
            if (isChecked(resourceIds[save.ordinal()])) {
                saves.add(save);
            }
        }
        return saves;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);

        switch (requestCode) {
            case REQUEST_CODE_ALIGNMENT:
                if (resultCode != RESULT_CANCELED) {
                    final Bundle bundle = resultIntent.getExtras();
                    final EnumSet<Alignment> alignments = (EnumSet<Alignment>) Objects.requireNonNull(bundle).get(INTENT_EXTRA_DATA_OBJECT);
                    helper.setAlignments(alignments);
                }
                break;

            case REQUEST_CODE_ABILITY:
                if (resultCode != RESULT_CANCELED) {
                    final Bundle bundle = resultIntent.getExtras();
                    final int[] classAbilitiesData = Objects.requireNonNull(bundle).getIntArray(INTENT_EXTRA_DATA_OBJECT);
                    final AbilityService abilityService = gameSystem.getAbilityService();
                    final List<Ability> allAbilities = gameSystem.getAllAbilities();
                    final List<ClassAbility> classAbilities = new ArrayList<>(Objects.requireNonNull(classAbilitiesData).length / 2);
                    for (int i = 0; i < classAbilitiesData.length; i = i + 2) {
                        final int abilityId = classAbilitiesData[i];
                        final int level = classAbilitiesData[i + 1];
                        final Ability ability = abilityService.getAbilityById(abilityId, allAbilities);
                        final ClassAbility classAbility = new ClassAbility(ability);
                        classAbility.setLevel(level);
                        classAbilities.add(classAbility);
                    }
                    helper.setClassAbilities(classAbilities);
                }
                break;

            case REQUEST_CODE_SKILL:
                if (resultCode != RESULT_CANCELED) {
                    final Bundle bundle = resultIntent.getExtras();
                    final int[] skillIds = Objects.requireNonNull(bundle).getIntArray(INTENT_EXTRA_DATA_OBJECT);
                    final List<Skill> skills = new ArrayList<>(Objects.requireNonNull(skillIds).length);
                    final SkillService skillService = gameSystem.getSkillService();
                    final List<Skill> allSkills = gameSystem.getAllSkills();
                    for (final int skillId : skillIds) {
                        final Skill skill = skillService.getSkillById(skillId, allSkills);
                        skills.add(skill);
                    }
                    helper.setSkills(skills);
                }
                break;

            default:
                throw new IllegalStateException("Result code (" + requestCode + ") is unknown");
        }
    }
}
