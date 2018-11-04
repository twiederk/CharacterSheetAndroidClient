package com.android.ash.charactersheet.gui.admin.item;

import java.util.Arrays;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormularActivity;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndPositiveDecimalNumberViewController;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.QualityType;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.ItemService;

/**
 * Base activity to handle the GUI of creating and editing items. The properties of all items are filled and read from
 * the GUI (name, type, cost, weight, magic and description). Derive from this class for specific item properties.
 *
 * @param <T>
 *            The specific item type like weapon, armor or good.
 */
public abstract class ItemAdministrationActivity<T> extends FormularActivity<Item> {

    protected ItemService itemService;
    protected GameSystem gameSystem;

    private NumberViewController costController;
    private NumberViewController weightController;

    @Override
    protected void onCreate(final Bundle savedInstanceState, final int layoutResourceId) {
        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        gameSystem = application.getGameSystem();
        itemService = gameSystem.getItemService();

        super.onCreate(savedInstanceState, layoutResourceId);
    }

    @Override
    protected void fillViews() {
        setText(form.getName(), R.id.item_administration_name);
        setSpinner(R.id.item_administration_type, getTypeAdapter(), getTypeSelectedId());
        setCostNumberView();
        setWeightNumberView();
        setSpinner(R.id.item_administration_quality_type, getQualityAdapter(), getQualitySelectedId());
        setText(form.getDescription(), R.id.item_administration_desc);
    }

    private void setCostNumberView() {
        costController = new ZeroAndPositiveDecimalNumberViewController(form.getCost());
        setNumberView(costController, R.id.item_administration_cost);
    }

    private void setWeightNumberView() {
        weightController = new ZeroAndPositiveDecimalNumberViewController(form.getWeight());
        setNumberView(weightController, R.id.item_administration_weight);
    }

    private void setNumberView(final NumberViewController controller, final int resourceId) {
        final StepNumberView numberView = (StepNumberView) findViewById(resourceId);
        numberView.setEditable(true);
        numberView.setController(controller);
    }

    protected abstract SpinnerAdapter getTypeAdapter();

    protected abstract int getTypeSelectedId();

    protected SpinnerAdapter getQualityAdapter() {
        final ArrayAdapter<QualityType> qualityTypeArrayAdapter = new QualityTypeArrayAdapter(this, displayService,
                Arrays.asList(QualityType.values()));
        return qualityTypeArrayAdapter;
    }

    protected int getQualitySelectedId() {
        return form.getQualityType().ordinal();
    }

    @Override
    protected void fillForm() {
        form.setName(getTextOfTextView(R.id.item_administration_name));
        form.setCost(costController.getNumber().floatValue());
        form.setWeight(weightController.getNumber().floatValue());
        form.setQualityType((QualityType) getSelectedItemOfSpinner(R.id.item_administration_quality_type));
        form.setDescription(getTextOfTextView(R.id.item_administration_desc));
    }

}
