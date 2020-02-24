package com.android.ash.charactersheet.gui.admin.clazz.skill;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.SkillService;
import com.d20charactersheet.framework.boc.util.SkillComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays all skills with a checkbox to select them as class skills.
 */
public class CharacterClassSkillActivity extends LogAppCompatActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    private GameSystem gameSystem;
    private List<SkillModel> skillModels;

    /**
     * Creates table of skills with checkboxes to select them as class skills. At the bottom an ok and cancel button is
     * offered.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_class_skill);
        setToolbar();

        gameSystem = gameSystemHolder.getValue().getGameSystem();

        setSkills();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.character_class_administration_skills_label);
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

    private void setSkills() {
        final List<Skill> characterClassSkills = getSkillsFromIntent();
        skillModels = createSkillModels(characterClassSkills);
        fillTable();
    }

    private List<Skill> getSkillsFromIntent() {
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        final int[] skillIds = Objects.requireNonNull(extras).getIntArray(INTENT_EXTRA_DATA_OBJECT);
        final List<Skill> skills = new ArrayList<>(Objects.requireNonNull(skillIds).length);
        final SkillService skillService = gameSystem.getSkillService();
        final List<Skill> allSkills = gameSystem.getAllSkills();
        for (final int skillId : skillIds) {
            final Skill skill = skillService.getSkillById(skillId, allSkills);
            skills.add(skill);
        }

        return skills;
    }

    private List<SkillModel> createSkillModels(final List<Skill> characterClassSkills) {
        final List<Skill> allSkills = gameSystem.getAllSkills();
        Collections.sort(allSkills, new SkillComparator());
        final List<SkillModel> skillModels = new ArrayList<>();
        for (final Skill skill : allSkills) {
            final SkillModel skillModel = new SkillModel(skill, characterClassSkills.contains(skill));
            skillModels.add(skillModel);
        }
        return skillModels;
    }

    private void fillTable() {
        final TableLayout characterClassTableLayout = findViewById(R.id.character_class_skill_table);
        for (final SkillModel skillModel : skillModels) {
            final TableRow tableRow = createTableRow(skillModel);
            characterClassTableLayout.addView(tableRow);
        }
    }

    private TableRow createTableRow(final SkillModel skillModel) {
        final TableRow tableRow = new TableRow(this);
        final CheckBox checkBox = createCheckBox(skillModel);
        tableRow.addView(checkBox);
        return tableRow;
    }

    private CheckBox createCheckBox(final SkillModel skillModel) {
        final CheckBox checkBox = new CheckBox(this);
        checkBox.setText(skillModel.getSkill().getName());
        checkBox.setChecked(skillModel.isClassSkill());
        checkBox.setOnClickListener(new SkillCheckboxOnClickListener(skillModel));
        return checkBox;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            save();
            finish();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            save();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void save() {
        final List<Skill> skills = getSkillsFromSkillModels();
        setResultAndFinish(skills);
    }

    private List<Skill> getSkillsFromSkillModels() {
        final List<Skill> skills = new ArrayList<>();
        for (final SkillModel skillModel : skillModels) {
            Logger.debug("skillModel: " + skillModel);
            if (skillModel.isClassSkill()) {
                skills.add(skillModel.getSkill());
            }
        }
        return skills;
    }

    private void setResultAndFinish(final List<Skill> skills) {
        final Intent resultIntent = new Intent();
        final int[] skillIds = new int[skills.size()];
        for (int i = 0; i < skillIds.length; i++) {
            skillIds[i] = skills.get(i).getId();
        }
        resultIntent.putExtra(INTENT_EXTRA_DATA_OBJECT, skillIds);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
