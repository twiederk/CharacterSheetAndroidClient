package com.android.ash.charactersheet.gui.admin.item.good;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.item.ItemAdministrationActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.GoodType;

import java.util.Arrays;

/**
 * Form to create of edit a good.
 */
public abstract class GoodAdministrationActivity extends ItemAdministrationActivity<Good> {

    private Good good;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.good_administration);
        good = (Good) form;
    }

    @Override
    protected void fillViews() {
        super.fillViews();
    }

    @Override
    protected SpinnerAdapter getTypeAdapter() {
        return new GoodTypeArrayAdapter(this, displayService, Arrays.asList(GoodType.values()));
    }

    @Override
    protected int getTypeSelectedId() {
        return good.getGoodType().ordinal();
    }

    @Override
    protected void fillForm() {
        super.fillForm();
        good.setGoodType((GoodType) getSelectedItemOfSpinner(R.id.item_administration_type));
        Logger.debug("good: " + form);
    }

}
