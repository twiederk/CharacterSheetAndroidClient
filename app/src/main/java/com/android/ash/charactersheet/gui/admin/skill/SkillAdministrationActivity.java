package com.android.ash.charactersheet.gui.admin.skill;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.KnownSpellsTable;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpellsPerDayTable;
import com.d20charactersheet.framework.boc.service.AbilityService;
import com.d20charactersheet.framework.boc.service.CharacterClassService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.SkillService;
import com.d20charactersheet.framework.boc.service.SpelllistService;
import com.d20charactersheet.framework.boc.util.CharacterClassComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Base class for layout of skill creation and edition. Only the methods getSkill and saveSkill must be overwritten. The
 * other methods handle the skill administration layout, independently of creation or edition mode.
 */
public abstract class SkillAdministrationActivity extends FormActivity<Skill> {

    GameSystem gameSystem;
    SkillService skillService;
    private AbilityService abilityService;
    private CharacterClassService characterClassService;
    private SpelllistService spelllistService;
    private List<CharacterClassSkillModel> characterClassSkillModels;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        characterClassSkillModels = new LinkedList<>();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.skill_administration;
    }

    @Override
    protected void createServices() {
        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        skillService = gameSystem.getSkillService();
        abilityService = gameSystem.getAbilityService();
        characterClassService = gameSystem.getCharacterClassService();
        spelllistService = gameSystem.getSpelllistService();
    }

    @Override
    protected void fillViews() {
        form = skillService.getSkillDescription(form);
        setText(form.getName(), R.id.skill_administration_name);
        setText(form.getDescription(), R.id.skill_administration_description);
        setCheckBox(form.isUntrained(), R.id.skill_administration_untrained);
        setAttributeSpinner();
        createCheckBoxesOfCharacterClasses();
    }

    private void setAttributeSpinner() {
        final ArrayAdapter<Attribute> attributeArrayAdapter = new AttributeArrayAdapter(this, displayService,
                Arrays.asList(Attribute.values()));
        setSpinner(R.id.skill_administration_attribute, attributeArrayAdapter, form.getAttribute().ordinal());
    }

    private void createCheckBoxesOfCharacterClasses() {
        final List<CheckBox> checkBoxes = createCheckBoxes();
        createTable(checkBoxes);
    }

    private List<CheckBox> createCheckBoxes() {
        final List<CheckBox> checkBoxes = new ArrayList<>();
        for (final CharacterClass characterClass : getSortedCharacterClasses()) {
            checkBoxes.add(createCheckBox(characterClass));
        }
        return checkBoxes;
    }

    private void createTable(final List<CheckBox> checkBoxes) {
        final TableLayout characterClassTableLayout = findViewById(R.id.skill_administration_character_class_table);
        boolean odd = true;
        final int numberOfCheckBoxes = checkBoxes.size();
        for (int i = 0; i < numberOfCheckBoxes; i += 2, odd = !odd) {
            final TableRow tableRow = new TableRow(this);
            tableRow.addView(checkBoxes.get(i));
            if (i + 1 < numberOfCheckBoxes) {
                tableRow.addView(checkBoxes.get(i + 1));
            }
            characterClassTableLayout.addView(tableRow);
        }
    }

    private List<CharacterClass> getSortedCharacterClasses() {
        final List<Skill> allSkills = skillService.getAllSkills();
        final List<Spelllist> allSpelllists = spelllistService.getAllSpelllists(spelllistService.getAllSpells());
        final List<KnownSpellsTable> allKnownSpellsTables = spelllistService.getAllKnownSpellsTables();
        final List<SpellsPerDayTable> allSpellsPerDayTables = spelllistService.getAllSpellsPerDayTables();

        final List<Ability> allAbilities = abilityService.getAllAbilities(allSpelllists, allKnownSpellsTables,
                allSpellsPerDayTables);
        final List<CharacterClass> characterClasses = characterClassService.getAllCharacterClasses(allSkills,
                allAbilities);
        Collections.sort(characterClasses, new CharacterClassComparator());
        return characterClasses;
    }

    private CheckBox createCheckBox(final CharacterClass characterClass) {
        final CheckBox checkBox = new CheckBox(this);
        checkBox.setText(characterClass.getName());
        final boolean classSkill = characterClass.getSkills().contains(form);
        final CharacterClassSkillModel model = new CharacterClassSkillModel(characterClass, classSkill);
        checkBox.setChecked(model.isClassSkill());
        checkBox.setOnClickListener(new CharacterClassCheckboxOnClickListener(model));
        characterClassSkillModels.add(model);
        return checkBox;
    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.skill_administration_name));
        form.setDescription(getTextOfTextView(R.id.skill_administration_description));
        form.setAttribute((Attribute) getSelectedItemOfSpinner(R.id.skill_administration_attribute));
        form.setUntrained(isChecked(R.id.skill_administration_untrained));
    }

    @Override
    protected void saveForm() {
        Toast.makeText(this, R.string.toast_saving, Toast.LENGTH_LONG).show();
        characterClassService.deleteSkill(form);
        final List<CharacterClass> characterClasses = getCharacterClassesToAddSkillTo();
        characterClassService.addSkill(form, characterClasses);
    }

    private List<CharacterClass> getCharacterClassesToAddSkillTo() {
        final List<CharacterClass> characterClasses = new LinkedList<>();
        for (final CharacterClassSkillModel model : characterClassSkillModels) {
            Logger.debug(model.getCharacterClass().getName() + ": " + model.isClassSkill());
            if (model.isClassSkill()) {
                characterClasses.add(model.getCharacterClass());
            }
        }
        return characterClasses;
    }
}
