package com.android.ash.charactersheet.gui.admin.spelllist;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.widget.ListAdapter;
import com.android.ash.charactersheet.gui.widget.ListModel;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Stores all spell assignments of a spell list. Displays each spell assignment and allows to adjust the level. Allows
 * to delete the spell assignment.
 */
public class SpelllevelAdapter extends ListAdapter<SpelllevelView> {

    private final GameSystem gameSystem;

    /**
     * Create SpelllevelAdapter with given data.
     * 
     * @param context
     *            The activity the adapter is used by.
     * @param gameSystem
     *            The game system.
     * @param listModel
     *            The list model of the adapter.
     */
    public SpelllevelAdapter(final Context context, final GameSystem gameSystem,
            final ListModel<SpelllevelView> listModel) {
        super(context, R.layout.listitem_spelllevel, listModel);
        this.gameSystem = gameSystem;
    }

    @Override
    protected void fillView(final View view, final Object item) {
        final SpelllevelView spellLevelView = (SpelllevelView) item;

        final TextView nameTextView = view.findViewById(R.id.item_name);
        nameTextView.setText(spellLevelView.getSpell().getName());

        final StepNumberView goodNumberView = view.findViewById(R.id.item_number);
        goodNumberView.setController(new SpelllevelNumberViewController(gameSystem, spellLevelView));

        final ImageButton deleteButton = view.findViewById(R.id.item_delete);
        deleteButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                gameSystem.deleteSpellFromSpelllist(spellLevelView.getSpelllist(), spellLevelView.getSpell());
                getListModel().remove(spellLevelView);

            }
        });
    }
}
