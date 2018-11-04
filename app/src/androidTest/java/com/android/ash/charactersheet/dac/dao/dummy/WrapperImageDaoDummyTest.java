package com.android.ash.charactersheet.dac.dao.dummy;

import android.test.AndroidTestCase;

public class WrapperImageDaoDummyTest extends AndroidTestCase {

    private ImageDaoDummyTest imageDaoDummyTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        imageDaoDummyTest = new ImageDaoDummyTest(getContext());
        imageDaoDummyTest.setUp();
    }

    public void testGetImage() throws Exception {
        imageDaoDummyTest.testGetImage();
    }

    public void testCreateImage() throws Exception {
        imageDaoDummyTest.testCreateImage();
    }

    public void testDeleteImage() throws Exception {
        imageDaoDummyTest.testDeleteImage();
    }

}
