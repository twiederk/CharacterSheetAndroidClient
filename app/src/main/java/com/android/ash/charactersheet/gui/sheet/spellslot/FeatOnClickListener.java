package com.android.ash.charactersheet.gui.sheet.spellslot;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.MessageManager;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.service.DisplayService;

/**
 * Handles click on metamagic feat checkbox in spell slot. It checks if it is possible to add the metamagic feat to the
 * spell slot, if not it displays a message.
 */
public class FeatOnClickListener implements OnClickListener {

    private final SpellSlotActivityModel spellSlotActivityModel;
    private final Feat feat;

    private final MessageManager messageManager;

    /**
     * Instanciate FeatOnClickListner for each separate feat checkbox.
     * 
     * @param context
     *            The context of the activity.
     * @param displayService
     *            The display service.
     * @param spellSlotActivityModel
     *            The spell slot.
     * @param feat
     *            The feat to check.
     */
    public FeatOnClickListener(final Context context, final DisplayService displayService,
            final SpellSlotActivityModel spellSlotActivityModel, final Feat feat) {
        this.spellSlotActivityModel = spellSlotActivityModel;
        this.feat = feat;

        messageManager = new MessageManager(context, displayService);
    }

    @Override
    public void onClick(final View view) {
        final CheckBox checkBox = (CheckBox) view;

        if (checkBox.isChecked()) {
            final int levelReduction = getLevelReduction();
            final SpellSlot spellSlot = spellSlotActivityModel.getSpellSlot();
            final int spelllistMinLevel = spellSlot.getSpelllistAbilities().get(0).getSpelllist().getMinLevel();
            if (spellSlot.getLevel() - levelReduction >= spelllistMinLevel) {
                spellSlotActivityModel.add(feat);
            } else {
                messageManager.showInfoMessage(R.string.spellslot_message_error_to_many_metamagicfeats);
                checkBox.setChecked(false);
            }
        } else {
            spellSlotActivityModel.remove(feat);
        }
    }

    private int getLevelReduction() {
        int levelReduction = 0;
        for (final Feat metamagicFeat : spellSlotActivityModel.getSpellSlot().getMetamagicFeats()) {
            levelReduction = levelReduction + metamagicFeat.getSpellSlot();
        }
        levelReduction = levelReduction + feat.getSpellSlot();
        return levelReduction;
    }
}
