package com.android.ash.charactersheet.gui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * The StrokeDrawable renders the shape with a stroked border.
 */
public class StrokeDrawable extends ShapeDrawable {
    private final Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * Creates StrokeDrawable with the given shape.
     * 
     * @param shape
     *            The shape to render with stroke border.
     */
    public StrokeDrawable(final Shape shape) {
        super(shape);
        strokePaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * Returns the paint of the stroke border.
     * 
     * @return The paint of the stroke border.
     */
    public Paint getStrokePaint() {
        return strokePaint;
    }

    @Override
    protected void onDraw(final Shape s, final Canvas c, final Paint p) {
        s.draw(c, p);
        s.draw(c, strokePaint);
    }
}
