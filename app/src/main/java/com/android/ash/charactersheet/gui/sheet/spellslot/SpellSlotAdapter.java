package com.android.ash.charactersheet.gui.sheet.spellslot;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.ListAdapter;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;

import java.util.Iterator;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * Displays the spell slots of a spell list for a character. Each spell slot is displayed with the spell name, the spell
 * list abilitie names, the difficulty class, the school and the metamagic feats. The background is displayed in the
 * color of the school. Cast spells are displayed with grey background and the name of the spell striked-through.
 * Touching a spell slot calls the SpellSlotActivity.
 */
public class SpellSlotAdapter extends ListAdapter<Object> {

    private static final String COMMA = ", ";
    private static final String DC = "DC: ";
    private static final String PARENTHESIS_LEFT = "(";
    private static final String PARENTHESIS_RIGHT = ")";

    private final Character character;
    private final DisplayService displayService;
    private final RuleService ruleService;

    /**
     * Instanciate SpellSlotAdapter to display spell slot of a character for each of its classes.
     * 
     * @param context
     *            The activity context.
     * @param character
     *            The character the spell slots belong to.
     * @param displayService
     *            The service to display data.
     * @param ruleService
     *            The service to calculate difficulty class.
     * @param spellSlotModel
     *            The model of the spell slot.
     */
    public SpellSlotAdapter(final Context context, final Character character, final DisplayService displayService,
            final RuleService ruleService, final SpellSlotModel spellSlotModel) {
        super(context, R.layout.listitem_spellslot, spellSlotModel);
        this.character = character;
        this.ruleService = ruleService;
        this.displayService = displayService;
        spellSlotModel.addObserver(this);
    }

    @Override
    protected void fillView(final View view, final Object item) {

        if (item instanceof LevelView) {
            final LevelView levelView = (LevelView) item;
            configureViews(view, true, false, null);
            fillLevelView(view, levelView);
        } else {
            final SpellSlotView spellSlotView = (SpellSlotView) item;
            configureViews(view, false, spellSlotView.getLevelView().isExpanded(), spellSlotView.getSpellSlot());
            fillSpellSlotView(view, spellSlotView);
        }
    }

    private void configureViews(final View view, final boolean levelView, final boolean levelExpanded,
            final SpellSlot spellSlot) {
        final View spellLevelTitleView = view.findViewById(R.id.spelllevel_title);

        final View tableView = view.findViewById(android.R.id.background);
        final TextView spellNameView = view.findViewById(R.id.spell_name);
        final View difficultyClassView = view.findViewById(R.id.difficulty_class);
        final View spellSchoolView = view.findViewById(R.id.spell_school);
        final View spelllistAbilityView = view.findViewById(R.id.spelllist_ability);
        final View metamagicFeatView = view.findViewById(R.id.metamagic_feat);
        final View dividerView = view.findViewById(R.id.spell_divider);

        if (levelView) {
            // LevelView
            view.setVisibility(View.VISIBLE);
            spellLevelTitleView.setVisibility(View.VISIBLE);
            spellNameView.setVisibility(View.GONE);
            difficultyClassView.setVisibility(View.GONE);
            spellSchoolView.setVisibility(View.GONE);
            spelllistAbilityView.setVisibility(View.GONE);
            metamagicFeatView.setVisibility(View.GONE);
            dividerView.setVisibility(View.VISIBLE);
        } else {
            // SpellView
            if (levelExpanded) {
                view.setVisibility(View.VISIBLE);
                spellLevelTitleView.setVisibility(View.GONE);
                spellNameView.setVisibility(View.VISIBLE);
                if (spellSlot.isCast()) {
                    spellNameView.setPaintFlags(spellNameView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    spellNameView.setPaintFlags(spellNameView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
                difficultyClassView.setVisibility(View.VISIBLE);
                spellSchoolView.setVisibility(View.VISIBLE);
                spelllistAbilityView.setVisibility(View.VISIBLE);
                metamagicFeatView.setVisibility(View.VISIBLE);
                dividerView.setVisibility(View.VISIBLE);

                final int color = getColor(view, spellSlot);
                tableView.setBackgroundColor(color);

                if (spellSlot.getMetamagicFeats().isEmpty()) {
                    metamagicFeatView.setVisibility(View.GONE);
                } else {
                    metamagicFeatView.setVisibility(View.VISIBLE);
                    metamagicFeatView.setBackgroundColor(color);
                }
            } else {
                view.setVisibility(View.GONE);
                spellLevelTitleView.setVisibility(View.GONE);
                spellNameView.setVisibility(View.GONE);
                difficultyClassView.setVisibility(View.GONE);
                spellSchoolView.setVisibility(View.GONE);
                spelllistAbilityView.setVisibility(View.GONE);
                metamagicFeatView.setVisibility(View.GONE);
                dividerView.setVisibility(View.GONE);
            }
        }
    }

    private int getColor(final View view, final SpellSlot spellSlot) {
        final Context context = view.getContext();
        final Spell spell = spellSlot.getSpell();
        if (spellSlot.isCast()) {
            return ContextCompat.getColor(context, R.color.cast_spell);
        }
        if (SpellSlot.EMPTY_SPELL.equals(spell)) {
            return ContextCompat.getColor(context, R.color.empty_spell);
        }
        switch (spell.getSchool()) {
        case ABJURATION:
            return ContextCompat.getColor(context, R.color.abjuration);
        case CONJURATION:
            return ContextCompat.getColor(context, R.color.conjuration);
        case DIVINATION:
            return ContextCompat.getColor(context, R.color.divination);
        case ENCHANTMENT:
            return ContextCompat.getColor(context, R.color.enchantment);
        case EVOCATION:
            return ContextCompat.getColor(context, R.color.evocation);
        case ILLUSION:
            return ContextCompat.getColor(context, R.color.illusion);
        case NECROMANCY:
            return ContextCompat.getColor(context, R.color.necormancy);
        case TRANSMUTATION:
            return ContextCompat.getColor(context, R.color.transmuation);
        case UNIVERSAL:
            return ContextCompat.getColor(context, R.color.universal);
        default:
            return ContextCompat.getColor(context, R.color.cast_spell);
        }
    }

    private void fillLevelView(final View view, final LevelView levelView) {
        final TextView titleTextView = view.findViewById(R.id.spelllevel_title);
        final String text = displayService.getDisplaySpellSlotLevel(levelView.getLevel());
        titleTextView.setText(text);
    }

    private void fillSpellSlotView(final View view, final SpellSlotView spellSlotView) {
        final SpellSlot spellSlot = spellSlotView.getSpellSlot();
        final Spell spell = spellSlot.getSpell();
        final List<SpelllistAbility> spelllistAbilities = spellSlot.getSpelllistAbilities();
        final List<Feat> metamagicFeats = spellSlot.getMetamagicFeats();

        final TextView nameTextView = view.findViewById(R.id.spell_name);
        nameTextView.setText(spell.getName());

        final TextView difficultyClassView = view.findViewById(R.id.difficulty_class);
        difficultyClassView.setText(getDifficultyClassText(spellSlot));

        final TextView spellSchoolView = view.findViewById(R.id.spell_school);
        spellSchoolView.setText(getSchoolText(spell));

        final TextView spelllistAbilityView = view.findViewById(R.id.spelllist_ability);
        spelllistAbilityView.setText(getSpelllistAblilityText(spelllistAbilities));

        final TextView metamagicFeatView = view.findViewById(R.id.metamagic_feat);
        metamagicFeatView.setText(getMetamagicFeatText(metamagicFeats));

    }

    private String getDifficultyClassText(final SpellSlot spellSlot) {
        final StringBuilder text = new StringBuilder();
        if (!SpellSlot.EMPTY_SPELL.equals(spellSlot.getSpell())) {
            final int difficultyClass = ruleService.getSpellSaveDifficultyClass(character, spellSlot);
            text.append(DC).append(difficultyClass);
        }
        return text.toString();
    }

    private CharSequence getSchoolText(final Spell spell) {
        final StringBuilder text = new StringBuilder();
        if (!SpellSlot.EMPTY_SPELL.equals(spell)) {
            text.append(PARENTHESIS_LEFT).append(displayService.getDisplaySchoolShort(spell.getSchool()))
                    .append(PARENTHESIS_RIGHT);
        }
        return text.toString();
    }

    private String getSpelllistAblilityText(final List<SpelllistAbility> spelllistAbilities) {
        final StringBuilder text = new StringBuilder();
        for (final Iterator<SpelllistAbility> iterator = spelllistAbilities.iterator(); iterator.hasNext();) {
            final SpelllistAbility spelllistAbility = iterator.next();
            text.append(spelllistAbility.getName());
            if (iterator.hasNext()) {
                text.append(COMMA);
            }
        }
        return text.toString();
    }

    private String getMetamagicFeatText(final List<Feat> metamagicFeats) {
        final StringBuilder text = new StringBuilder();
        if (!metamagicFeats.isEmpty()) {
            for (final Iterator<Feat> iterator = metamagicFeats.iterator(); iterator.hasNext();) {
                final Feat feat = iterator.next();
                text.append(feat.getName());
                if (iterator.hasNext()) {
                    text.append(COMMA);
                }
            }
        }
        return text.toString();
    }

}
