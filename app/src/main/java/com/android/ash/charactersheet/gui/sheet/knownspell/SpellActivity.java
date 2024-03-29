package com.android.ash.charactersheet.gui.sheet.knownspell;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.util.SlideActivity;
import com.android.ash.charactersheet.gui.util.TableTagHander;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.SpelllistService;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

import kotlin.Lazy;

/**
 * Displays detail information of a spell.
 */
public class SpellActivity extends SlideActivity {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);

    private GameSystem gameSystem;
    private DisplayService displayService;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spell);

        gameSystem = gameSystemHolder.getValue().getGameSystem();
        displayService = Objects.requireNonNull(gameSystem).getDisplayService();

        final Spell spell = getSpellFromIntent();
        setToolbar(spell);
        setSpell(spell);
        logEventSpellDescription(spell);
    }

    private void setToolbar(Spell spell) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(spell.getName());
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.drawable.icon);
    }


    private Spell getSpellFromIntent() {
        final Bundle extras = getIntent().getExtras();
        final int spellId = Objects.requireNonNull(extras).getInt(INTENT_EXTRA_DATA_OBJECT);
        final SpelllistService spelllistService = gameSystem.getSpelllistService();
        Spell spell = spelllistService.findSpellById(spellId, gameSystem.getAllSpells());
        spell = spelllistService.getSpellDescription(spell);
        Logger.debug("spell: " + spell);
        return spell;
    }

    private void setSpell(final Spell spell) {
        setText(R.id.spell_school, getText(R.string.spell_school, displayService.getDisplaySpellSchool(spell)));
        setText(R.id.spell_components,
                getText(R.string.spell_component, displayService.getDisplaySpellComponents(spell)));
        setText(R.id.spell_casting_time,
                getText(R.string.spell_casting_time, displayService.getDisplayCastingTime(spell.getCastingTime())));
        setText(R.id.spell_range, getText(R.string.spell_range, spell.getRange()));
        setText(R.id.spell_effect, getText(R.string.spell_effect, spell.getEffect()));
        setText(R.id.spell_duration, getText(R.string.spell_duration, spell.getDuration()));
        setText(R.id.spell_saving_throw, getText(R.string.spell_saving_throw, spell.getSavingThrow()));
        setText(R.id.spell_spell_resistance,
                getText(R.string.spell_spell_resistance,
                        displayService.getDisplaySpellResistance(spell.getSpellResistance())));
        setText(R.id.spell_description, getText(R.string.spell_description, spell.getDescription()));
        setText(R.id.spell_mat_component, getText(R.string.spell_mat_component, spell.getMaterialComponent()));
        setText(R.id.spell_focus, getText(R.string.spell_focus, spell.getFocus()));
    }

    private void setText(final int viewResourceId, final CharSequence text) {
        final TextView textView = findViewById(viewResourceId);
        textView.setText(text);
    }

    private Spanned getText(final int labelResourceId, final String text) {
        final String label = getResources().getString(labelResourceId);
        final StringBuilder output = new StringBuilder();
        output.append("<b>").append(label).append(":</b> ");
        if (text != null) {
            output.append(text);
        }
        return Html.fromHtml(output.toString(), Html.FROM_HTML_MODE_LEGACY, null, new TableTagHander());
    }

    private void logEventSpellDescription(Spell spell) {
        Bundle bundle = new Bundle();
        bundle.putString(FBAnalytics.Param.SPELL_NAME, spell.getName());
        firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.SPELL_DESCRIPTION_SHOW, bundle);
    }

}
