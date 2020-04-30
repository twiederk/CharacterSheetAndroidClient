package com.android.ash.charactersheet.dac.dao.dummy;

import android.content.Context;

import com.d20charactersheet.framework.dac.dao.BaseImageDaoTest;

import org.junit.Before;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

@RunWith(AndroidJUnit4.class)
public class ImageDaoDummyInstrumentedTest extends BaseImageDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        imageDao = new DummyImageDao(context);
    }

}
