package com.android.ash.charactersheet.gui.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.service.AndroidImageService;
import com.android.ash.charactersheet.gui.widget.DisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.List;

/**
 * Creates an item of the character list. Displaying an face image of the character to the right. The character name to
 * the left and beyond the race, class and level of the character.
 */
public class CharacterArrayAdapter extends DisplayArrayAdapter<Character> {

    private final AndroidImageService imageService;

    /**
     * Creates CharacterArrayAdapter using the list of characters to create views of each character.
     * 
     * @param context
     *            The context to display the view in.
     * @param displayService
     *            The display service to display data.
     * @param imageService
     *            The image service to retrieve images.
     * @param itemViewResourceId
     *            The resource if of the layout file to display a list item.
     * @param characters
     *            The list of all characters of the list.
     */
    public CharacterArrayAdapter(final Context context, final DisplayService displayService,
            final AndroidImageService imageService, final int itemViewResourceId, final List<Character> characters) {
        super(context, displayService, itemViewResourceId, characters);
        this.imageService = imageService;
    }

    @Override
    protected void fillView(final View view, final Character character) {
        // face of character
        final ImageView faceImageView = view.findViewById(R.id.character_face);
        final Bitmap bitmap = imageService.getBitmap(character.getThumbImageId());
        faceImageView.setImageBitmap(bitmap);

        // name of character
        final TextView nameTextView = view.findViewById(R.id.character_name);
        nameTextView.setText(character.getName());

        // stats of character
        final TextView statsTextView = view.findViewById(R.id.character_stats);
        statsTextView.setText(getStats(character));
    }

    private CharSequence getStats(final Character character) {
        return character.getRace().getName() +
                ", " +
                displayService.getDisplayClassLevels(character.getClassLevels());
    }

}
