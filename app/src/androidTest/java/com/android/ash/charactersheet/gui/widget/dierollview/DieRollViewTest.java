package com.android.ash.charactersheet.gui.widget.dierollview;

import android.content.Context;
import android.graphics.Bitmap;

import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.DieRoll;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DieRollViewTest {

    private DieRollView dieRollView;
    private TestDieRollController controller;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dieRollView = new DieRollView(context, null);
        dieRollView.setScale(1.0f);
        controller = new TestDieRollController();
    }

    @Test
    public void testCreateMultipleDieBitmap1D6() {
        final DieRoll dieRoll = createDieRoll(new int[]{1});
        assertBitmap(dieRoll, 64, 64);
    }

    @Test
    public void testCreateMultipleDieBitmap2D6() {
        final DieRoll dieRoll = createDieRoll(new int[]{1, 2});
        assertBitmap(dieRoll, 128, 64);
    }

    @Test
    public void testCreateMultipleDieBitmap3D6() {
        final DieRoll dieRoll = createDieRoll(new int[]{1, 2, 3});
        assertBitmap(dieRoll, 192, 64);
    }

    @Test
    public void testCreateMultipleDieBitmap4D6() {
        final DieRoll dieRoll = createDieRoll(new int[]{1, 2, 3, 4});
        assertBitmap(dieRoll, 192, 128);
    }

    @Test
    public void testCreateMultipleDieBitmap5D6() {
        final DieRoll dieRoll = createDieRoll(new int[]{1, 2, 3, 4, 5});
        assertBitmap(dieRoll, 192, 128);
    }

    @Test
    public void testCreateMultipleDieBitmap6D6() {
        final DieRoll dieRoll = createDieRoll(new int[]{1, 2, 3, 4, 5, 6});
        assertBitmap(dieRoll, 192, 128);
    }

    @Test
    public void testCreateMultipleDieBitmap7D6() {
        final DieRoll dieRoll = createDieRoll(new int[]{1, 2, 3, 4, 5, 6, 1});
        assertBitmap(dieRoll, 192, 192);
    }

    private void assertBitmap(final DieRoll dieRoll, final int width, final int height) {
        controller.setDieRoll(dieRoll);
        dieRollView.setController(controller);

        final Bitmap bitmap = dieRollView.createMultipleDieBitmap();

        assertNotNull(bitmap);
        assertEquals(width, bitmap.getWidth());
        assertEquals(height, bitmap.getHeight());

    }

    private DieRoll createDieRoll(final int[] rolls) {
        final DieRoll dieRoll = new DieRoll();
        dieRoll.setDie(Die.D6);
        dieRoll.setRolls(rolls);
        dieRoll.setBonus(0);
        return dieRoll;
    }

    public static class TestDieRollController implements DieRollViewController {

        private DieRoll dieRoll;

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public DieRoll getDieRoll() {
            return dieRoll;
        }

        @Override
        public int getColor() {
            return 0;
        }

        @Override
        public String getSubtitle() {
            return null;
        }

        void setDieRoll(final DieRoll dieRoll) {
            this.dieRoll = dieRoll;
        }
    }

    @Test
    public void testGetBitmapWidth() {
        int bitmapWidth = dieRollView.getBitmapWidth(1, 64);
        assertEquals(64, bitmapWidth);

        bitmapWidth = dieRollView.getBitmapWidth(2, 64);
        assertEquals(128, bitmapWidth);

        bitmapWidth = dieRollView.getBitmapWidth(3, 64);
        assertEquals(192, bitmapWidth);

        bitmapWidth = dieRollView.getBitmapWidth(4, 64);
        assertEquals(192, bitmapWidth);
    }

    @Test
    public void testGetBitmapHeight() {
        int bitmapHeight = dieRollView.getBitmapHeight(1, 64);
        assertEquals(64, bitmapHeight);

        bitmapHeight = dieRollView.getBitmapHeight(2, 64);
        assertEquals(64, bitmapHeight);

        bitmapHeight = dieRollView.getBitmapHeight(3, 64);
        assertEquals(64, bitmapHeight);

        bitmapHeight = dieRollView.getBitmapHeight(4, 64);
        assertEquals(128, bitmapHeight);

        bitmapHeight = dieRollView.getBitmapHeight(5, 64);
        assertEquals(128, bitmapHeight);

        bitmapHeight = dieRollView.getBitmapHeight(6, 64);
        assertEquals(128, bitmapHeight);

        bitmapHeight = dieRollView.getBitmapHeight(7, 64);
        assertEquals(192, bitmapHeight);
    }

    @Test
    public void testGetX() {
        final int[] expectedX = new int[] { 0, 64, 128, 0, 64, 128, 0 };
        for (int i = 0; i < 7; i++) {
            assertEquals(expectedX[i], dieRollView.getX(i, 64));
        }
    }

}
