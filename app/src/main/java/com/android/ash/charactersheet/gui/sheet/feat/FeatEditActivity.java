package com.android.ash.charactersheet.gui.sheet.feat;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.LogActivity;
import com.d20charactersheet.framework.boc.model.CharacterFeat;

import java.util.Objects;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;

/**
 * The activity allows to edit the category of the feat.
 */
public class FeatEditActivity extends LogActivity {

    private TextView categoryTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        setContentView(R.layout.feat_edit);
        super.onCreate(savedInstanceState);

        final CharacterFeat characterFeat = getCharacterFeatFromIntent();
        setTitle(characterFeat.getFeat().getName());
        categoryTextView = findViewById(R.id.feat_edit_category);
        categoryTextView.setText(characterFeat.getCategory());
    }

    private CharacterFeat getCharacterFeatFromIntent() {
        final Bundle bundle = getIntent().getExtras();
        return (CharacterFeat) Objects.requireNonNull(bundle).get(INTENT_EXTRA_DATA_OBJECT);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            saveData();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            saveData();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void saveData() {
        final Intent resultIntent = new Intent();
        resultIntent.putExtra(INTENT_EXTRA_DATA_OBJECT, categoryTextView.getText().toString());
        setResult(RESULT_OK, resultIntent);
    }

}
