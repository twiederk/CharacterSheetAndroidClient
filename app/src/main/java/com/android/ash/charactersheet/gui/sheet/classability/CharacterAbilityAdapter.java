package com.android.ash.charactersheet.gui.sheet.classability;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.ExpandableListItem;
import com.android.ash.charactersheet.gui.util.TableTagHander;
import com.android.ash.charactersheet.gui.widget.ListAdapter;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Displays an ability of a class in the list of class abilities. The name of the ability is displayed in the 1st line
 * and always visible. The 2nd to 4th line can be expanded an collapsed. The 2nd displays the type of the ability. The
 * 3rd line contains the class of the ability and the level it is gained. The 4th line holds the description of the
 * ability.
 */
public class CharacterAbilityAdapter extends ListAdapter<ExpandableListItem> {

    private final DisplayService displayService;
    private final Resources resources;
    private final Html.TagHandler tableTagHandler;
    private final CharacterAbilityModel characterAbilityModel;
    private final CharacterService characterService;

    /**
     * Creates Adapter to display class abilities in a list view.
     * 
     * @param context
     *            The context to display the list in.
     * @param displayService
     *            Used to format data properly.
     * @param characterService
     *            The service to handle character data.
     * @param itemViewResourceId
     *            The id of the layout file.
     * @param characterAbilityModel
     *            The class abilities to display.
     */
    public CharacterAbilityAdapter(final Context context, final DisplayService displayService,
            final CharacterService characterService, final int itemViewResourceId,
            final CharacterAbilityModel characterAbilityModel) {
        super(context, itemViewResourceId, characterAbilityModel);
        this.displayService = displayService;
        this.characterService = characterService;
        this.resources = context.getResources();
        this.characterAbilityModel = characterAbilityModel;
        this.tableTagHandler = new TableTagHander();
    }

    @Override
    protected void fillView(final View view, final Object item) {
        final ExpandableListItem expandableListItem = (ExpandableListItem) item;
        final CharacterAbilityListItem characterAbilityListItem = (CharacterAbilityListItem) expandableListItem
                .getObject();

        setOwnedCheckBox(view, characterAbilityListItem);

        final TextView nameTextView = view.findViewById(R.id.ability_name);
        nameTextView.setText(characterAbilityListItem.getAbilityName());

        if (expandableListItem.isExpanded()) {
            setVisibility(view, View.VISIBLE);
            setText(view, characterAbilityListItem);
        } else {
            setVisibility(view, View.GONE);
        }
    }

    private void setOwnedCheckBox(final View view, final CharacterAbilityListItem characterAbilityListItem) {
        final CheckBox ownedCheckBox = view.findViewById(R.id.ability_owned);

        if (characterAbilityModel.isShowAll()) {
            ownedCheckBox.setVisibility(View.VISIBLE);
            ownedCheckBox.setChecked(characterAbilityListItem.isOwned());
            ownedCheckBox.setOnClickListener(view1 -> {
                final CheckBox checkBox = (CheckBox) view1;
                characterAbilityListItem.setOwned(checkBox.isChecked());
                characterService.updateCharacterAbility(characterAbilityListItem.getCharacterAbility());
            });
        } else {
            ownedCheckBox.setVisibility(View.GONE);
        }
    }

    private void setVisibility(final View view, final int visibility) {
        final TableRow secondRow = view.findViewById(R.id.second_row);
        final TableRow thirdRow = view.findViewById(R.id.third_row);
        final TableRow forthRow = view.findViewById(R.id.forth_row);

        secondRow.setVisibility(visibility);
        thirdRow.setVisibility(visibility);
        forthRow.setVisibility(visibility);
    }

    private void setText(final View view, final CharacterAbilityListItem classAbilityListItem) {
        final TextView typeTextView = view.findViewById(R.id.ability_type);
        typeTextView.setText(displayService.getDisplayAbilityType(classAbilityListItem.getAbilityType()));

        final TextView characterClassTextView = view.findViewById(R.id.ability_character_class);
        final String characterClassText = resources.getString(R.string.character_class_ability_class) + ": "
                + classAbilityListItem.getCharacterClassName();
        characterClassTextView.setText(characterClassText);

        final TextView levelTextView = view.findViewById(R.id.ability_level);
        final String levelText = resources.getString(R.string.character_class_ability_level) + ": "
                + classAbilityListItem.getLevel();
        levelTextView.setText(levelText);

        final TextView descriptionTextView = view.findViewById(R.id.ability_description);
        final Spanned htmlDescription = Html.fromHtml(classAbilityListItem.getAbilityDescription(), Html.FROM_HTML_MODE_LEGACY, null, tableTagHandler);
        descriptionTextView.setText(htmlDescription);
    }
}
