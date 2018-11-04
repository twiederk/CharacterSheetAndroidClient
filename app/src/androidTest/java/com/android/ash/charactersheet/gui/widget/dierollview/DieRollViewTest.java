package com.android.ash.charactersheet.gui.widget.dierollview;

import android.graphics.Bitmap;
import android.test.AndroidTestCase;

import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.DieRoll;

public class DieRollViewTest extends AndroidTestCase {

    DieRollView dieRollView;
    TestDieRollController controller;

    @Override
    protected void setUp() throws Exception {
        dieRollView = new DieRollView(getContext(), null);
        dieRollView.setScale(1.0f);
        controller = new TestDieRollController();
    }

    public void testCreateMultipleDieBitmap1D6() throws Exception {
        final DieRoll dieRoll = createDieRoll(Die.D6, new int[] { 1 }, 0);
        assertBitmap(dieRoll, 64, 64);
    }

    public void testCreateMultipleDieBitmap2D6() throws Exception {
        final DieRoll dieRoll = createDieRoll(Die.D6, new int[] { 1, 2 }, 0);
        assertBitmap(dieRoll, 128, 64);
    }

    public void testCreateMultipleDieBitmap3D6() throws Exception {
        final DieRoll dieRoll = createDieRoll(Die.D6, new int[] { 1, 2, 3 }, 0);
        assertBitmap(dieRoll, 192, 64);
    }

    public void testCreateMultipleDieBitmap4D6() throws Exception {
        final DieRoll dieRoll = createDieRoll(Die.D6, new int[] { 1, 2, 3, 4 }, 0);
        assertBitmap(dieRoll, 192, 128);
    }

    public void testCreateMultipleDieBitmap5D6() throws Exception {
        final DieRoll dieRoll = createDieRoll(Die.D6, new int[] { 1, 2, 3, 4, 5 }, 0);
        assertBitmap(dieRoll, 192, 128);
    }

    public void testCreateMultipleDieBitmap6D6() throws Exception {
        final DieRoll dieRoll = createDieRoll(Die.D6, new int[] { 1, 2, 3, 4, 5, 6 }, 0);
        assertBitmap(dieRoll, 192, 128);
    }

    public void testCreateMultipleDieBitmap7D6() throws Exception {
        final DieRoll dieRoll = createDieRoll(Die.D6, new int[] { 1, 2, 3, 4, 5, 6, 1 }, 0);
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

    private DieRoll createDieRoll(final Die die, final int[] rolls, final int bonus) {
        final DieRoll dieRoll = new DieRoll();
        dieRoll.setDie(die);
        dieRoll.setRolls(rolls);
        dieRoll.setBonus(bonus);
        return dieRoll;
    }

    public class TestDieRollController implements DieRollViewController {

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

        public void setDieRoll(final DieRoll dieRoll) {
            this.dieRoll = dieRoll;
        }
    }

    public void testGetBitmapWidth() throws Exception {
        int bitmapWidth = dieRollView.getBitmapWidth(1, 64);
        assertEquals(64, bitmapWidth);

        bitmapWidth = dieRollView.getBitmapWidth(2, 64);
        assertEquals(128, bitmapWidth);

        bitmapWidth = dieRollView.getBitmapWidth(3, 64);
        assertEquals(192, bitmapWidth);

        bitmapWidth = dieRollView.getBitmapWidth(4, 64);
        assertEquals(192, bitmapWidth);
    }

    public void testGetBitmapHeight() throws Exception {
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

    public void testGetX() throws Exception {
        final int[] expectedX = new int[] { 0, 64, 128, 0, 64, 128, 0 };
        for (int i = 0; i < 7; i++) {
            assertEquals(expectedX[i], dieRollView.getX(i, 64));
        }
    }

}
