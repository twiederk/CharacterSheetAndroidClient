package com.android.ash.charactersheet.gui.sheet.knownspell;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.ListAdapter;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.KnownSpell;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * Adapter to display list items in SpelllistPageFragment.
 */
public class SpelllistAdapter extends ListAdapter<Object> {

    private final DisplayService displayService;
    private final CharacterService characterService;
    private final RuleService ruleService;
    private final Character character;
    private final SpelllistModel spelllistModel;
    private final KnownSpellPageModel showAllModel;

    /**
     * Instanciates SpelllistAdapter.
     * 
     * @param context
     *            The context of the activity.
     * @param displayService
     *            The service to display data.
     * @param characterService
     *            The service to handle characters.
     * @param ruleService
     *            RuleService to get number of known spells.
     * @param spelllistModel
     *            The model of the spell list to display.
     * @param showAllModel
     *            Model containing the info about show all.
     * @param character
     *            The character using the spell list.
     */
    public SpelllistAdapter(final Context context, final DisplayService displayService,
            final CharacterService characterService, final RuleService ruleService,
            final SpelllistModel spelllistModel, final KnownSpellPageModel showAllModel, final Character character) {
        super(context, R.layout.listitem_knownspell, spelllistModel);
        this.displayService = displayService;
        this.characterService = characterService;
        this.ruleService = ruleService;
        this.character = character;
        this.spelllistModel = spelllistModel;
        this.showAllModel = showAllModel;
    }

    @Override
    protected void fillView(final View view, final Object item) {
        if (item instanceof LevelItem) {
            final LevelItem levelItem = (LevelItem) item;
            configureViews(view, true, false, levelItem.hasKnownSpells());
            fillLevelItemView(view, levelItem);
        } else {
            final SpellItem spellItem = (SpellItem) item;
            configureViews(view, false, spellItem.getLevelItem().isExpanded(), false);
            fillSpellItemView(view, spellItem);
        }
    }

    private void configureViews(final View view, final boolean levelView, final boolean levelExpanded,
            final boolean levelHasKnownSpells) {
        final View spellLevelTitleView = view.findViewById(R.id.spelllevel_title);
        final View numberOfKnownSpellsView = view.findViewById(R.id.spelllevel_number_of_known_spells);

        final View knownSpellCheckBox = view.findViewById(R.id.spell_known_spell);
        final View spellNameView = view.findViewById(R.id.spell_name);
        final View shortDescriptionView = view.findViewById(R.id.spell_short_description);
        final View dividerView = view.findViewById(R.id.spell_divider);

        if (levelView) {
            // LevelView
            if (showAllModel.isShowAll() || levelHasKnownSpells) {
                view.setVisibility(View.VISIBLE);
                spellLevelTitleView.setVisibility(View.VISIBLE);
                numberOfKnownSpellsView.setVisibility(View.VISIBLE);
                dividerView.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
                spellLevelTitleView.setVisibility(View.GONE);
                numberOfKnownSpellsView.setVisibility(View.GONE);
                dividerView.setVisibility(View.GONE);
            }
            knownSpellCheckBox.setVisibility(View.GONE);
            spellNameView.setVisibility(View.GONE);
            shortDescriptionView.setVisibility(View.GONE);
        } else {
            // SpellView
            if (levelExpanded) {
                view.setVisibility(View.VISIBLE);

                spellLevelTitleView.setVisibility(View.GONE);
                numberOfKnownSpellsView.setVisibility(View.GONE);
                if (showAllModel.isShowAll()) {
                    knownSpellCheckBox.setVisibility(View.VISIBLE);
                } else {
                    knownSpellCheckBox.setVisibility(View.GONE);
                }
                spellNameView.setVisibility(View.VISIBLE);
                shortDescriptionView.setVisibility(View.VISIBLE);
                dividerView.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
                spellLevelTitleView.setVisibility(View.GONE);
                numberOfKnownSpellsView.setVisibility(View.GONE);
                knownSpellCheckBox.setVisibility(View.GONE);
                spellNameView.setVisibility(View.GONE);
                shortDescriptionView.setVisibility(View.GONE);
                dividerView.setVisibility(View.GONE);
            }
        }
    }

    private void fillLevelItemView(final View view, final LevelItem levelItem) {
        final TextView titleTextView = view.findViewById(R.id.spelllevel_title);
        String text = displayService.getDisplaySpellLevel(levelItem.getLevel());
        titleTextView.setText(text);

        final TextView numberOfKnowSpellsTextView = view
                .findViewById(R.id.spelllevel_number_of_known_spells);
        final int numberOfKnownSpells = levelItem.getNumberOfKnownSpells();
        final int maxNumberOfKnownSpells = ruleService.getNumberOfKnownSpells(character,
                spelllistModel.getSpelllistAbility(), levelItem.getLevel());
        final int numberOfSpells = spelllistModel.getSpelllistAbility().getSpelllist()
                .getSpellsOfLevel(levelItem.getLevel()).size();
        text = displayService
                .getDisplayNumberOfKnownSpells(numberOfKnownSpells, maxNumberOfKnownSpells, numberOfSpells);
        numberOfKnowSpellsTextView.setText(text);
    }

    private void fillSpellItemView(final View view, final SpellItem spellItem) {

        if (showAllModel.isShowAll()) {
            final CheckBox knownSpellCheckBox = view.findViewById(R.id.spell_known_spell);
            knownSpellCheckBox.setChecked(spellItem.isKnownSpell());
            knownSpellCheckBox.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(final View view) {
                    final CheckBox checkBox = (CheckBox) view;
                    spellItem.setKnownSpell(checkBox.isChecked());
                    if (checkBox.isChecked()) {
                        createKnownSpell();
                    } else {
                        deleteKnownSpell();
                    }
                    notifyDataSetChanged();
                }

                private void createKnownSpell() {
                    final KnownSpell knownSpell = new KnownSpell();
                    knownSpell.setSpell(spellItem.getSpell());
                    knownSpell.setSpelllist(spelllistModel.getSpelllistAbility().getSpelllist());
                    characterService.createKnownSpell(character, knownSpell);
                }

                private void deleteKnownSpell() {
                    final KnownSpell knownSpell = spelllistModel.getKnownSpellBySpellId(spellItem.getSpell());
                    if (knownSpell != null) {
                        characterService.deleteKnownSpell(character, knownSpell);
                    }

                }
            });

        }

        final TextView nameTextView = view.findViewById(R.id.spell_name);
        nameTextView.setText(spellItem.getName());

        final TextView titleTextView = view.findViewById(R.id.spell_short_description);
        titleTextView.setText(spellItem.getShortDescription());
    }
}
