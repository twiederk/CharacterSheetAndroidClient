package com.android.ash.charactersheet.gui.admin.item.good;

import java.util.Arrays;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.admin.item.ItemAdministrationActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.GoodType;

/**
 * Form to create of edit a good.
 */
public abstract class GoodAdministrationActivity extends ItemAdministrationActivity<Good> {

    private Good good;

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
        final ArrayAdapter<GoodType> weaponTypeArrayAdapter = new GoodTypeArrayAdapter(this, displayService,
                Arrays.asList(GoodType.values()));
        return weaponTypeArrayAdapter;
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
