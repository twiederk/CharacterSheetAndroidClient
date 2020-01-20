package com.android.ash.charactersheet.dac.dao.dummy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.d20charactersheet.framework.dac.dao.BaseImageDaoTest;

import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ImageDaoDummyTest extends BaseImageDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        imageDao = new DummyImageDao(context);
    }

}
