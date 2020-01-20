package com.android.ash.charactersheet.gui.admin.clazz.alignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.android.ash.charactersheet.CharacterSheetApplication;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.service.DisplayService;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * Displays all alignments. Alignments can be checked to add them to a character class.
 */
public class CharacterClassAlignmentActivity extends LogActivity {

    private DisplayService displayService;

    private List<AlignmentModel> alignmentModels;

    /**
     * Creates table with all alignments and ok/cancel buttons at the bottom. Each alignment with a checkbox to select
     * it as a character class.
     * 
     * @see com.android.ash.charactersheet.gui.util.LogActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_class_alignment);
        setTitle(R.string.character_class_administration_alignments_label);

        final CharacterSheetApplication application = (CharacterSheetApplication) getApplication();
        displayService = application.getGameSystem().getDisplayService();

        setAlignments();
    }

    private void setAlignments() {
        final EnumSet<Alignment> alignments = getAlignmentsFromIntent();
        alignmentModels = createAlignmentModels(alignments);
        fillTable();
    }

    @SuppressWarnings("unchecked")
    private EnumSet<Alignment> getAlignmentsFromIntent() {
        final Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        return (EnumSet<Alignment>) Objects.requireNonNull(extras).getSerializable(INTENT_EXTRA_DATA_OBJECT);
    }

    private List<AlignmentModel> createAlignmentModels(final EnumSet<Alignment> alignments) {
        final List<AlignmentModel> alignmentModels = new ArrayList<>();
        for (final Alignment alignment : Alignment.values()) {
            final AlignmentModel alignmentModel = new AlignmentModel(alignment, alignments.contains(alignment));
            alignmentModels.add(alignmentModel);
        }
        return alignmentModels;
    }

    private void fillTable() {
        final TableLayout characterClassTableLayout = findViewById(R.id.character_class_alignment_table);
        for (final AlignmentModel alignmentModel : alignmentModels) {
            final TableRow tableRow = createTableRow(alignmentModel);
            characterClassTableLayout.addView(tableRow);
        }
    }

    private TableRow createTableRow(final AlignmentModel alignmentModel) {
        final TableRow tableRow = new TableRow(this);
        final CheckBox checkBox = createCheckBox(alignmentModel);
        tableRow.addView(checkBox);
        return tableRow;
    }

    private CheckBox createCheckBox(final AlignmentModel alignmentModel) {
        final CheckBox checkBox = new CheckBox(this);
        checkBox.setText(displayService.getDisplayAlignment(alignmentModel.getAlignment()));
        checkBox.setChecked(alignmentModel.isClassAlignment());
        checkBox.setOnClickListener(new AlignmentCheckboxOnClickListener(alignmentModel));
        return checkBox;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            save();
            finish();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            save();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void save() {
        final EnumSet<Alignment> alignments = getAlignmentsFromAlignmentModels();
        setResultAndFinish(alignments);
    }

    private EnumSet<Alignment> getAlignmentsFromAlignmentModels() {
        final EnumSet<Alignment> alignments = EnumSet.noneOf(Alignment.class);
        for (final AlignmentModel alignmentModel : alignmentModels) {
            Logger.debug("alignmentModel: " + alignmentModel);
            if (alignmentModel.isClassAlignment()) {
                alignments.add(alignmentModel.getAlignment());
            }
        }
        return alignments;
    }

    private void setResultAndFinish(final EnumSet<Alignment> alignments) {
        final Intent resultIntent = new Intent();
        resultIntent.putExtra(INTENT_EXTRA_DATA_OBJECT, alignments);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
