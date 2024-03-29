package com.android.ash.charactersheet.gui.sheet.spellslot;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.android.ash.charactersheet.CharacterHolder;
import com.android.ash.charactersheet.FBAnalytics;
import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Iterator;
import java.util.Objects;

import kotlin.Lazy;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import static org.koin.java.KoinJavaComponent.inject;

/**
 * Displays spell slot to select spell for. Displays spell list ability the spell slot belongs to. Displays spells to
 * select in a spinner. Displays metamagic feats to select.
 */
public class SpellSlotActivity extends FormActivity<SpellSlot> {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
    private final Lazy<CharacterHolder> characterHolder = inject(CharacterHolder.class);
    private final Lazy<FirebaseAnalytics> firebaseAnalytics = inject(FirebaseAnalytics.class);

    private static final Object COMMA = ", ";
    private GameSystem gameSystem;
    private Character character;
    private SpellSlotActivityModel spellSlotActivityModel;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_spellslot;
    }

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
        character = characterHolder.getValue().getCharacter();
    }

    @Override
    protected String getHeading() {
        return getString(R.string.spellslot_title);
    }

    @Override
    protected SpellSlot createForm() {
        final int spellSlotId = Objects.requireNonNull(getIntent().getExtras()).getInt(INTENT_EXTRA_DATA_OBJECT);
        for (final SpellSlot spellSlot : character.getSpellSlots()) {
            if (spellSlot.getId() == spellSlotId) {
                spellSlotActivityModel = new SpellSlotActivityModel(gameSystem.getRuleService(), character, spellSlot);
                return spellSlot;
            }
        }
        throw new IllegalStateException("Can't find spell slot with id: " + spellSlotId);
    }

    @Override
    protected void fillViews() {
        setText(displayAbilities(), R.id.spellslot_abilities);
        setFeats();
        setSpellSelection();
    }

    private String displayAbilities() {
        final StringBuilder text = new StringBuilder();
        for (final Iterator<SpelllistAbility> iterator = form.getSpelllistAbilities().iterator(); iterator.hasNext(); ) {
            final SpelllistAbility spelllistAbility = iterator.next();
            text.append(spelllistAbility.getName());
            if (iterator.hasNext()) {
                text.append(COMMA);
            }
        }
        return text.toString();
    }

    private void setFeats() {
        final TableLayout featTableLayout = findViewById(R.id.spellslot_feat_table);
        for (final Feat feat : character.getMetamagicFeats()) {
            final TableRow tableRow = createTableRow(feat);
            featTableLayout.addView(tableRow);
        }
    }

    private TableRow createTableRow(final Feat feat) {
        final TableRow tableRow = new TableRow(this);
        final CheckBox checkBox = createCheckBox(feat);
        tableRow.addView(checkBox);
        return tableRow;
    }

    private CheckBox createCheckBox(final Feat feat) {
        final CheckBox checkBox = new CheckBox(this);
        checkBox.setText(displayService.getDisplayMetamagicFeat(feat));
        checkBox.setChecked(form.getMetamagicFeats().contains(feat));
        checkBox.setOnClickListener(new FeatOnClickListener(this, gameSystem.getDisplayService(),
                spellSlotActivityModel, feat));
        return checkBox;
    }

    private void setSpellSelection() {
        final Spinner spinner = findViewById(R.id.spellslot_spell);
        final SpinnerAdapter adapter = new SpellSelectionAdapter(this, spinner, displayService, spellSlotActivityModel);
        setSpinner(R.id.spellslot_spell, adapter, spellSlotActivityModel.getSpellPosition());
    }

    @Override
    protected void fillForm() {
        form.setSpell((Spell) getSelectedItemOfSpinner(R.id.spellslot_spell));
        form.setCast(false);
    }

    @Override
    protected void saveForm() {
        gameSystem.getCharacterService().updateSpellSlot(form);
        setResult(RESULT_OK);
        logEventSpellSlot(form);
    }

    private void logEventSpellSlot(SpellSlot form) {
        Bundle bundle = new Bundle();
        bundle.putString(FBAnalytics.Param.SPELL_NAME, form.getSpell().getName());
        firebaseAnalytics.getValue().logEvent(FBAnalytics.Event.SPELL_SLOT_ASSIGN, bundle);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            save();
        }
        return true;
    }

}
