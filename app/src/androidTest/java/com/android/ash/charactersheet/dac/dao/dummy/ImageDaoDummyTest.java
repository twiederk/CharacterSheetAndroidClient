package com.android.ash.charactersheet.dac.dao.dummy;

import android.content.Context;

import com.d20charactersheet.framework.dac.dao.BaseImageDaoTest;

public class ImageDaoDummyTest extends BaseImageDaoTest {

    private final Context context;

    public ImageDaoDummyTest(final Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        imageDao = new DummyImageDao(context);
    }

}
