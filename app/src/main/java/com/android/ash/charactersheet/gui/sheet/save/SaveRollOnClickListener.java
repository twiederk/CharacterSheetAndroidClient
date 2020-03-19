package com.android.ash.charactersheet.gui.sheet.save;

import android.content.res.Resources;

import com.android.ash.charactersheet.gui.widget.RollOnClickListener;
import com.android.ash.charactersheet.gui.widget.dierollview.DieRollView;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.DieRoll;
import com.d20charactersheet.framework.boc.model.Save;
import com.d20charactersheet.framework.boc.service.DisplayService;
import com.d20charactersheet.framework.boc.service.RuleService;

/**
 * Listener for save rolls.
 */
public class SaveRollOnClickListener extends RollOnClickListener {

    private final Save save;

    /**
     * Instantiates SaveRollOnClickListener.
     * 
     * @param character
     *            Character to roll save for.
     * @param save
     *            The save to roll.
     * @param displayService
     *            The service to display data.
     * @param ruleService
     *            The service to execute the save roll.
     * @param dieRollView
     *            The DieRollView to display the result.
     */
    public SaveRollOnClickListener(final Character character, final Save save, final DisplayService displayService,
            final RuleService ruleService, final DieRollView dieRollView) {
        super(character, displayService, ruleService, dieRollView);
        this.save = save;
    }

    @Override
    protected String getTitle(final Resources resources) {
        return displayService.getDisplaySave(save);
    }

    @Override
    protected DieRoll getDieRoll() {
        return ruleService.rollSave(character, save);
    }

}
