package com.android.ash.charactersheet.gui.widget.dierollview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.ash.charactersheet.R;

/**
 * View to display the result of a die roll. Displays a title at top and an optional subtitle at the buttom. In the
 * center a number of dice plus bonus and the result is displaye.
 */
public class DieRollView extends RelativeLayout {

    private static final int DIE_BITMAP_WIDTH = 64;
    private static final int DIE_BITMAP_HEIGHT = 64;
    private static final int MAX_NUMBER_OF_DICE_PER_ROW = 3;
    private static final int TEXT_SIZE = 15;

    private static final String PLUS_SIGN = " + ";
    private static final String EQUAL_SIGN = " = ";

    private static final int ID_TITLE = 1000;
    private static final int ID_DIE = 2000;
    private static final int ID_CALCULATION = 3000;
    private static final int ID_SUBTITLE = 4000;

    private static final Bitmap BITMAP_1_x_1 = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);

    private TextView titleTextView;
    private ImageView dieImageView;
    private TextView calculationTextView;
    private TextView subtitleTextView;

    private DieRollViewController controller;

    float scale;

    /**
     * Instanciates DieRollView from XML layout.
     * 
     * @param context
     *            The context of the view.
     * @param attributeSet
     *            The attributes from the XML layout.
     */
    public DieRollView(final Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
        scale = getResources().getDisplayMetrics().density;

        createLayout(context, attributeSet);
    }

    private void createLayout(final Context context, final AttributeSet attributeSet) {
        setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dicebox));

        titleTextView = new TextView(context, attributeSet);
        titleTextView.setId(ID_TITLE);
        titleTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        titleTextView.setTextColor(Color.BLACK);
        titleTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        titleTextView.setPadding(4, 4, 4, 4);
        titleTextView.setVisibility(View.VISIBLE);

        dieImageView = new ImageView(context);
        dieImageView.setId(ID_DIE);
        final RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relativeLayoutParams.addRule(RelativeLayout.BELOW, titleTextView.getId());
        dieImageView.setLayoutParams(relativeLayoutParams);

        calculationTextView = new TextView(context, attributeSet);
        calculationTextView.setId(ID_CALCULATION);
        final RelativeLayout.LayoutParams calculationLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        calculationLayoutParams.addRule(RelativeLayout.BELOW, titleTextView.getId());
        calculationLayoutParams.addRule(RelativeLayout.RIGHT_OF, dieImageView.getId());
        calculationTextView.setLayoutParams(calculationLayoutParams);
        calculationTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        calculationTextView.setTextColor(Color.BLACK);
        calculationTextView.setHeight((int) (DIE_BITMAP_HEIGHT * scale));
        calculationTextView.setGravity(Gravity.CENTER_VERTICAL);
        calculationTextView.setPadding(4, 4, 4, 4);
        calculationTextView.setVisibility(View.VISIBLE);

        subtitleTextView = new TextView(context, attributeSet);
        subtitleTextView.setId(ID_SUBTITLE);
        final RelativeLayout.LayoutParams subtitleLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        subtitleLayoutParams.addRule(RelativeLayout.BELOW, dieImageView.getId());
        subtitleTextView.setLayoutParams(subtitleLayoutParams);
        subtitleTextView.setTextColor(Color.BLACK);
        subtitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        subtitleTextView.setPadding(4, 4, 4, 4);
        subtitleTextView.setVisibility(View.VISIBLE);

        addView(titleTextView);
        addView(dieImageView);
        addView(calculationTextView);
        addView(subtitleTextView);
    }

    /**
     * Sets the controller of the view.
     * 
     * @param controller
     *            The controller to set.
     */
    public void setController(final DieRollViewController controller) {
        this.controller = controller;
        updateView();
    }

    private void updateView() {
        titleTextView.setText(controller.getTitle());
        dieImageView.setImageBitmap(getDieBitmap());
        calculationTextView.setText(getCalculationText());
        subtitleTextView.setText(controller.getSubtitle());
    }

    private Bitmap getDieBitmap() {
        Bitmap dieBitmap;
        final int numberOfRolls = controller.getDieRoll().getRolls().length;

        switch (numberOfRolls) {
        case 0:
            dieBitmap = BITMAP_1_x_1;
            break;
        case 1:
            dieBitmap = getSingleDieBitmap(controller.getDieRoll().getRolls()[0]);
            break;
        default:
            dieBitmap = createMultipleDieBitmap();
            break;
        }
        return dieBitmap;
    }

    private Bitmap getSingleDieBitmap(final int rollValue) {
        final int dieIndex = controller.getDieRoll().getDie().ordinal();
        final int colorIndex = getDieImages(controller.getColor());
        final int rollIndex = rollValue - 1;
        final int dieImage = DieImage.IMAGES[dieIndex][colorIndex][rollIndex];
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), dieImage);
        return bitmap;
    }

    private int getDieImages(final int color) {
        switch (color) {
        case Color.BLUE:
            return 0;
        case Color.RED:
            return 1;
        case Color.WHITE:
            return 2;
        default:
            return 0;
        }
    }

    Bitmap createMultipleDieBitmap() {
        final int[] rolls = controller.getDieRoll().getRolls();

        final int dieBitmapWidth = (int) (DIE_BITMAP_WIDTH * scale);
        final int dieBitmapHeight = (int) (DIE_BITMAP_HEIGHT * scale);

        final int bitmapWidth = getBitmapWidth(rolls.length, dieBitmapWidth);
        final int bitmapHeight = getBitmapHeight(rolls.length, dieBitmapHeight);

        final Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);

        for (int i = 0; i < rolls.length; i++) {
            final Bitmap singleDieBitmap = getSingleDieBitmap(rolls[i]);
            final int[] pixels = new int[dieBitmapWidth * dieBitmapHeight];
            singleDieBitmap.getPixels(pixels, 0, dieBitmapWidth, 0, 0, dieBitmapWidth, dieBitmapHeight);
            final int x = i % MAX_NUMBER_OF_DICE_PER_ROW * dieBitmapWidth;
            final int y = i / MAX_NUMBER_OF_DICE_PER_ROW * dieBitmapHeight;
            bitmap.setPixels(pixels, 0, dieBitmapWidth, x, y, dieBitmapWidth, dieBitmapHeight);
        }
        return bitmap;
    }

    int getX(final int index, final int dieBitmapWidth) {
        return index % MAX_NUMBER_OF_DICE_PER_ROW * dieBitmapWidth;
    }

    int getBitmapWidth(final int numberOfRolls, final int dieBitmapWidth) {
        int bitmapWidth;
        if (numberOfRolls < MAX_NUMBER_OF_DICE_PER_ROW) {
            bitmapWidth = dieBitmapWidth * numberOfRolls;
        } else {
            bitmapWidth = dieBitmapWidth * MAX_NUMBER_OF_DICE_PER_ROW;
        }
        return bitmapWidth;
    }

    int getBitmapHeight(final int numberOfRolls, final int dieBitmapHeight) {
        final int numberOfRows = (numberOfRolls - 1) / MAX_NUMBER_OF_DICE_PER_ROW + 1;
        return numberOfRows * dieBitmapHeight;
    }

    private String getCalculationText() {
        final StringBuilder text = new StringBuilder();
        text.append(PLUS_SIGN);
        text.append(Integer.toString(controller.getDieRoll().getBonus()));
        text.append(EQUAL_SIGN);
        text.append(Integer.toString(controller.getDieRoll().getResult()));
        return text.toString();
    }

    float getScale() {
        return scale;
    }

    void setScale(final float scale) {
        this.scale = scale;
    }

}
