package com.android.ash.charactersheet.gui.sheet.skill;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity;
import com.android.ash.charactersheet.gui.util.TableTagHander;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.SkillService;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays the description of a skill including some HTML tags.
 */
public class SkillDescriptionActivity extends LogAppCompatActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

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

        final GameSystem gameSystem = gameSystemHolder.getValue().getGameSystem();
        final SkillService skillService = Objects.requireNonNull(gameSystem).getSkillService();

        // get skill and fill description
        Skill skill = skillService.getSkillById(skillId, gameSystem.getAllSkills());
        skill = skillService.getSkillDescription(skill);
        return skill;
    }

    private void createLayout(final Skill skill) {
        setContentView(R.layout.skill_description);
        setToolbar(skill);

        final TextView descriptionTextView = findViewById(R.id.skill_description);
        final Spanned htmlDescription = Html.fromHtml(skill.getDescription(), null, new TableTagHander());
        descriptionTextView.setText(htmlDescription);
    }

    private void setToolbar(Skill skill) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(skill.getName());
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }

}
