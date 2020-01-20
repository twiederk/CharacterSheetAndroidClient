package com.android.ash.charactersheet.gui.sheet.skill;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.android.ash.charactersheet.gui.util.TableTagHander;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.SkillService;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Displays the description of a skill including some HTML tags.
 */
public class SkillDescriptionActivity extends LogActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Skill skill = getSkill();
        createLayout(skill);
    }

    private Skill getSkill() {
        // get skill id from intent
        final Bundle bundle = getIntent().getExtras();
        final int skillId = Objects.requireNonNull(bundle).getInt(INTENT_EXTRA_DATA_OBJECT);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        final GameSystem gameSystem = application.getGameSystem();
        final SkillService skillService = gameSystem.getSkillService();

        // get skill and fill description
        Skill skill = skillService.getSkillById(skillId, gameSystem.getAllSkills());
        skill = gameSystem.getSkillService().getSkillDescription(skill);
        return skill;
    }

    private void createLayout(final Skill skill) {
        setContentView(R.layout.skill_description);
        setTitle(skill.getName());

        final TextView descriptionTextView = findViewById(R.id.skill_description);
        final Spanned htmlDescription = Html.fromHtml(skill.getDescription(), null, new TableTagHander());
        descriptionTextView.setText(htmlDescription);
    }
}
