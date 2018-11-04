package com.android.ash.charactersheet.gui.sheet.money;

import android.os.Bundle;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.EditActivity;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.SumNumberView;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndPositiveNumberViewController;
import com.d20charactersheet.framework.boc.model.Money;

/**
 * Form to edit money of the character. Platinum, gold, silver and copper coins are available. Only zero and positive
 * numbers of coins are allowed.
 */
public class MoneyEditActivity extends EditActivity {

    private NumberViewController platiumnController;
    private NumberViewController goldController;
    private NumberViewController silverController;
    private NumberViewController copperController;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setContentView(R.layout.activity_money_edit);
        setTitle(R.string.money_title);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setData() {
        createControllers();
        setControllers();
    }

    private void createControllers() {
        final Money money = character.getMoney();
        platiumnController = new ZeroAndPositiveNumberViewController(money.getPlatinum());
        goldController = new ZeroAndPositiveNumberViewController(money.getGold());
        silverController = new ZeroAndPositiveNumberViewController(money.getSilver());
        copperController = new ZeroAndPositiveNumberViewController(money.getCopper());
    }

    private void setControllers() {
        setController(R.id.money_platinum, platiumnController);
        setController(R.id.money_gold, goldController);
        setController(R.id.money_silver, silverController);
        setController(R.id.money_copper, copperController);
    }

    private void setController(final int numberViewResourceId, final NumberViewController controller) {
        final SumNumberView numberView = (SumNumberView) findViewById(numberViewResourceId);
        numberView.setController(controller);
    }

    @Override
    protected void saveData() {
        final Money money = new Money();
        money.setPlatinum(platiumnController.getNumber().intValue());
        money.setGold(goldController.getNumber().intValue());
        money.setSilver(silverController.getNumber().intValue());
        money.setCopper(copperController.getNumber().intValue());
        character.setMoney(money);
    }
}
