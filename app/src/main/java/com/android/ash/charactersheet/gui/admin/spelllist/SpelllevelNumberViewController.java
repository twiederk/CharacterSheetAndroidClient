package com.android.ash.charactersheet.gui.admin.spelllist;

import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.d20charactersheet.framework.boc.service.GameSystem;

/**
 * Allows to set level of spell in spell list. The level ranges from 0 to 9.
 */
public class SpelllevelNumberViewController implements NumberViewController {

    private final SpelllevelView spelllevelView;
    private final GameSystem gameSystem;

    /**
     * Creates new SpelllevelViewController.
     * 
     * @param gameSystem
     *            The game system.
     * @param spelllevelView
     *            The spell assignment to modify the level of.
     */
    public SpelllevelNumberViewController(final GameSystem gameSystem, final SpelllevelView spelllevelView) {
        this.gameSystem = gameSystem;
        this.spelllevelView = spelllevelView;
    }

    @Override
    public Number getNumber() {
        return spelllevelView.getLevel();
    }

    @Override
    public void increase() {
        if (spelllevelView.getLevel() < 9) {
            spelllevelView.setLevel(spelllevelView.getLevel() + 1);
            gameSystem.getSpelllistService().updateSpelllevel(spelllevelView.getSpelllist(), spelllevelView.getSpell(),
                    spelllevelView.getLevel());
        }
    }

    @Override
    public void decrease() {
        if (spelllevelView.getLevel() > 0) {
            spelllevelView.setLevel(spelllevelView.getLevel() - 1);
            gameSystem.getSpelllistService().updateSpelllevel(spelllevelView.getSpelllist(), spelllevelView.getSpell(),
                    spelllevelView.getLevel());
        }
    }

    @Override
    public void setNumber(final Number number) {
        spelllevelView.setLevel(number.intValue());
    }

    @Override
    public void decrease(final Number number) {
        if (spelllevelView.getLevel() - number.intValue() >= 0) {
            spelllevelView.setLevel(spelllevelView.getLevel() - number.intValue());
            gameSystem.getSpelllistService().updateSpelllevel(spelllevelView.getSpelllist(), spelllevelView.getSpell(),
                    spelllevelView.getLevel());
        }
    }

    @Override
    public void increase(final Number number) {
        if (spelllevelView.getLevel() + number.intValue() <= 9) {
            spelllevelView.setLevel(spelllevelView.getLevel() + number.intValue());
            gameSystem.getSpelllistService().updateSpelllevel(spelllevelView.getSpelllist(), spelllevelView.getSpell(),
                    spelllevelView.getLevel());
        }
    }

}
