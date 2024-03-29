package com.android.ash.charactersheet.gui.admin.item;

import android.widget.SpinnerAdapter;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.FormActivity;
import com.android.ash.charactersheet.gui.widget.numberview.NumberViewController;
import com.android.ash.charactersheet.gui.widget.numberview.StepNumberView;
import com.android.ash.charactersheet.gui.widget.numberview.ZeroAndPositiveDecimalNumberViewController;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.QualityType;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.ItemService;

import java.util.Arrays;
import java.util.Objects;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Base activity to handle the GUI of creating and editing items. The properties of all items are filled and read from
 * the GUI (name, type, cost, weight, magic and description). Derive from this class for specific item properties.
 *
 * @param <T>
 *            The specific item type like weapon, armor or good.
 */
public abstract class ItemAdministrationActivity<T> extends FormActivity<Item> {

    private final Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);

    public ItemService itemService;
    protected GameSystem gameSystem;

    private NumberViewController costController;
    private NumberViewController weightController;

    @Override
    protected void createServices() {
        gameSystem = gameSystemHolder.getValue().getGameSystem();
        itemService = Objects.requireNonNull(gameSystem).getItemService();
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
        final StepNumberView numberView = findViewById(resourceId);
        numberView.setEditable(true);
        numberView.setController(controller);
    }

    protected abstract SpinnerAdapter getTypeAdapter();

    protected abstract int getTypeSelectedId();

    private SpinnerAdapter getQualityAdapter() {
        return new QualityTypeArrayAdapter(this, displayService,
                Arrays.asList(QualityType.values()));
    }

    private int getQualitySelectedId() {
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
