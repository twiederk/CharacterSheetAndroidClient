package com.android.ash.charactersheet.gui.sheet.spellslot;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormularActivity;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.service.GameSystem;

import java.util.Iterator;
import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Displays spell slot to select spell for. Displays spell list ability the spell slot belongs to. Displays spells to
 * select in a spinner. Displays metamagic feats to select.
 */
public class SpellSlotActivity extends FormularActivity<SpellSlot> {

    private static final Object COMMA = ", ";
    private GameSystem gameSystem;
    private Character character;
    private SpellSlotActivityModel spellSlotActivityModel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        character = application.getCharacter();

        super.onCreate(savedInstanceState, R.layout.activity_spellslot);
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
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            save();
        }
        return true;
    }

}
